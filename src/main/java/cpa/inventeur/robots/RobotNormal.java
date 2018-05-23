package cpa.inventeur.robots;

import java.util.List;

import cpa.inventeur.cardsenums.Invention;
import cpa.inventeur.cardsenums.Inventor;
import cpa.inventeur.cardsenums.Skill;
import cpa.inventeur.controller.PlayerConsole;
import cpa.inventeur.controller.Table;

import static cpa.inventeur.cardsenums.Ticket.*;
import static java.util.logging.Level.*;

/**
 * @author Liu Jiaqi
 * @date 2018-4-14
 */
public class RobotNormal extends Robot {
    
    private List<Inventor> libre;
    Inventor bestInventor;
    Invention bestInvention;

    public RobotNormal(String name, PlayerConsole console) {
        table = Table.getInstance();
        this.console = console;
        this.name = name;
        libre = console.getLibres();
    }

    @Override
    public void toPlay() {
        if (hasLibreToUse() && !finishOneInv())
            if (findMaxMatch() == 0)
                this.setAllFree();
            else
                send(bestInventor, bestInvention); // send an inventor
        if (bestInvention.isFinished()) // if the invention is finished
            iFinish(bestInvention);
    }

    /**
     * @return true if has some inventor to use; or set all inventors free and
     *         return false;
     */
    boolean hasLibreToUse() {
        libre = console.getLibres();
        if (libre.isEmpty() && !console.useTicket(SETALLFREE)) {
            setAllFree();
            return false;
        }
        return true;
    }

    int findMaxMatch() {
        int match = 0;
        int maxmatch = 0;
        for (Invention invention : table.getNotFinished())
            for (Inventor inventor : console.getLibres()) {
                for (Skill skill : Skill.values()) {
                    int demande = invention.getDemandeValue(skill);
                    int has = inventor.getSkillValue(skill);
                    if (demande > 0)
                        match += demande - invention.residualDemande(has, demande);
                }
                if (maxmatch < match) {
                    maxmatch = match;
                    bestInvention = invention;
                    bestInventor = inventor;
                }
                match = 0;
            }
        return maxmatch;
    }

    @Override
    public String toString() {
        return name;
    }

    boolean finishOneInv() {
        for (Invention invention : table.getNotFinished())
            for (Inventor inventor : console.getLibres())
                if (toFinish(inventor, invention)) {
                    bestInvention = invention;
                    return send(inventor, invention);
                }
        return false;
    }

    /**
     * @param inventor
     * @param invention
     * @return TRUE if inventor can finish invention
     */
    boolean toFinish(Inventor inventor, Invention invention) {
        boolean flag = true;
        for (Skill skill : Skill.values()) {
            int demand = invention.getDemandeValue(skill);
            int has = inventor.getSkillValue(skill);
            flag &= has >= demand;
        }
        return flag;
    }

    @Override
    public void chooseTicket(Invention inv) {
        if (!console.pickTicket(inv, ADDONEPOINT)) {
            console.pickTicket(inv, SETALLFREE);
            LOG.log(INFO, "#{0} chose SETALLFREE", this);
        } else {
            LOG.log(INFO, "#{0} chose ADDONRPOINT", this);
        }
    }
}

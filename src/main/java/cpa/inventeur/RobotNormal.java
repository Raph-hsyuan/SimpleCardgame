package cpa.inventeur;

import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Level.*;
import static cpa.inventeur.Ticket.*;

/**
 * @author Liu Jiaqi
 * @date 2018-4-14
 */
public class RobotNormal implements Robot {
    private Table table;
    private String name;
    private PlayerConsole console;
    private static final Logger LOG = Logger.getLogger("RobotInfo");

    RobotNormal(String name, PlayerConsole console) {
        table = Table.getInstance();
        this.console = console;
        this.name = name;
    }

    @Override
    public void toPlay() {
        List<Inventor> libre;
        libre = console.getLibres();
        if (libre.isEmpty()) {
            if(!console.useTicket(SETALLFREE))
                setAllFree();
        } else {
            int match = 0;
            int maxmatch = 0;
            Inventor bestInventor = console.getLibres().get(0);
            Invention bestInvention = table.getNotFinished().get(0);
            for (Invention invention : table.getNotFinished()) {
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
            }
            if (maxmatch == 0)
                this.setAllFree();
            else {
                for (Invention invention : table.getNotFinished())
                    for (Inventor inventor : console.getLibres())
                        if (toFinish(inventor, invention)) {
                            bestInventor = inventor;
                            bestInvention = invention;
                        }
                send(bestInventor, bestInvention); // send an inventor
            }
            if (bestInvention.isFinished()) { // if the invention is finished
                iFinish(bestInvention);
            }
        }
    }

    private void send(Inventor inventor, Invention invention) {
        console.send(inventor, invention);
        StringBuilder done = new StringBuilder();
        done.append("# " + this + " sends " + inventor + "---->" + invention);
        LOG.log(INFO, "\nRobotInfo:\n{0}\n\n", done);
    }

    private void iFinish(Invention in) {
        StringBuilder fi = new StringBuilder();
        fi.append(this + " finishs " + in);
        LOG.log(INFO, "\ngetPoint :{0}\n",fi);
    }
    
    private void setAllFree() {
        console.setAllFree();
        LOG.log(INFO, "#{0} set All Free\n\n", this);
    }


    @Override
    public String toString() {
        return name;
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
    public void closeLogger() {
        LOG.setLevel(OFF);
    }

    @Override
    public PlayerColor getColor() {
        return console.color;
    }
    
    @Override
    public PlayerConsole getConsole() {
        return console;
    }

    @Override
    public void chooseTicket(Invention inv) {
        if(!console.pickTicket(inv, ADDONEPOINT)) {
            console.pickTicket(inv, SETALLFREE);
            LOG.log(INFO, "#{0} chose SETALLFREE", this);
        }else {
            LOG.log(INFO, "#{0} chose ADDONRPOINT", this);
        }
    }

    @Override
    public boolean useTicket(Ticket tik) {
        StringBuilder use = new StringBuilder();
        use.append(this+" use "+tik);
        LOG.log(INFO, "#{0}", use);
        return console.useTicket(tik);
    }
}

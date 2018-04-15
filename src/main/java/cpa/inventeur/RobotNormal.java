package cpa.inventeur;

import java.util.List;

/**
 * @author Liu Jiaqi
 * @date 2018-4-14
 */
public class RobotNormal implements Robot {
    private Table table;
    private String name;
    private PlayerConsole console;

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
            console.setAllFree();
            System.out.println(this + " set All Free");
        } else {
            int match = 0;
            int maxmatch = 0;
            Inventor bestInventor = console.getLibres().get(0);
            Invention bestInvention = table.getInventions().get(0);
            for (Invention invention : table.getInventions()) {
                for (Inventor inventor : console.getLibres()) {
                    if(toFinish(inventor,invention)) {
                        bestInventor = inventor;
                        bestInvention = invention;
                        break;
                    }
                    for (Skill skill : Skill.values()) {
                        int demande = invention.getDemandeValue(skill);
                        int has = inventor.getSkillValue(skill);
                        if (demande > 0) {
                            match += demande - invention.residualDemande(has, demande);
                        }
                    }
                    if (maxmatch < match) {
                        maxmatch = match;
                        bestInvention = invention;
                        bestInventor = inventor;
                    }
                    match = 0;
                }
            }
            System.out.println("+++++++++++++++++++++++++++");
            System.out.println("# " + this + " sends "+bestInventor+"---->" + bestInvention);
            console.send(bestInventor, bestInvention); // send an inventor
            if (bestInvention.isFinished()) { // if the invention is finished
                console.addPoint(); // add point
                System.out.println("# " + this + " gets one point!!");
                System.out.println("+++++++++++++++++++++++++++");
            }
        }
    }

    @Override
    public int getScore() {
        return console.getScore();
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
            flag&=inventor.getSkillValue(skill)>=invention.getDemandeValue(skill);
        }
        return flag;
    }
}

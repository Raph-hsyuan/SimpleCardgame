package cpa.inventeur;

import static cpa.inventeur.Skill.CHEMISTRY;
import static cpa.inventeur.Skill.MACHINERY;
import static cpa.inventeur.Skill.MATHS;
import static cpa.inventeur.Skill.PHYSICS;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * @author Wu Kejia
 * @author Huang Shenyuan
 * @date 2018-4-13
 */
public enum Invention {
    CAR(" CAR  ", 3, 1, 2, 2), PLANE("PLANE ", 2, 1, 4, 4), 
    BOAT(" BOAT ", 1, 4, 3, 2), BIKE(" BIKE ", 3, 3, 3, 3);

    private static final boolean FINISHED = true;
    private static final boolean INPROGRESS = false;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;
    private final String name;
    private boolean state;
    private final EnumMap<Skill, Integer> demande = new EnumMap<>(Skill.class);
    private final EnumMap<Skill, Integer> iniDemande = new EnumMap<>(Skill.class);
    List<Inventor> inventors = new ArrayList<>();

    Invention(String name, int phy, int che, int mac, int mat) {
        this.name = name;
        state = INPROGRESS;
        demande.put(PHYSICS, phy);
        demande.put(CHEMISTRY, che);
        demande.put(MACHINERY, mac);
        demande.put(MATHS, mat);
        iniDemande.putAll(demande);
    }

    boolean addInventor(Inventor inventor) {
        boolean flag = FAILED;
        for (EnumMap.Entry<Skill, Integer> entry : demande.entrySet()) {
            Skill key = entry.getKey();
            int needs = entry.getValue();
            int has = inventor.getSkillValue(key);
            if (needs > 0 && has > 0) {
                demande.replace(key, needs, residualDemande(has, needs));
                flag = SUCCESS;
            }
        }
        if(flag) inventors.add(inventor);
        return flag;
    }

    /**
     * @return true if all demandes have been met
     */
    boolean isFinished() {
        if (state) return state;
        int sum = 0;
        for (Integer find : demande.values())
            sum += find;
        if (sum == 0)
            state = FINISHED;
        else
            state = INPROGRESS;
        return state;
    }

    void setFinished() {
        this.state = FINISHED;
    }

    @Override
    public String toString() {
        String invention = new String("\n\t|--------------|\n\t|===>" + name + "<===|\n");
        if (this.isFinished())
            invention += "\t|-IS FINISHED -|\n";
        else
            invention += "\t|-IN PROGRESS -|\n";
        if (this.inventors.isEmpty())
            invention += "\t|---[      ]---|\n";
        else
            invention += "\t|---" + inventors + "---|\n";
        invention += "\t|--------------|\n";
        return invention;
    }

    int getDemandeValue(Skill skill) {
        return demande.get(skill);
    }

    int residualDemande(int has, int demande) {
        demande -= has;
        return demande <= 0 ? 0 : demande;
    }
    
    void initial() {
        demande.putAll(iniDemande);
        state = INPROGRESS;
        inventors.clear();
    }
}

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
    CAR(" CAR  ", 3, 1, 2, 2), PLANE("PLANE ", 2, 1, 4, 4), BOAT(" BOAT ", 1, 4, 3, 2), BIKE(" BIKE ", 3, 3, 3, 3);

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
        if (flag)
            inventors.add(inventor);
        return flag;
    }

    /**
     * @return true if all demandes have been met
     */
    boolean isFinished() {
        if (state)
            return state;
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

    public String toCard() {
        StringBuilder invention = new StringBuilder();
        invention.append("\n\t|--------------|\n\t|===>" + name + "<===|\n");
        if (this.isFinished())
            invention.append("\t|-IS FINISHED -|\n");
        else
            invention.append("\t|-IN PROGRESS -|\n");
        invention.append("\t|--------------|\n");
        for (Skill skill : demande.keySet()) {
            invention.append("\t|-" + skill + toStars(skill) + "\n");
        }
        return invention.toString();
    }

    @Override
    public String toString() {
        return name;
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

    String toStars(Skill skill) {
        StringBuilder stars = new StringBuilder();
        int iniNum = this.iniDemande.get(skill);
        int num = iniNum - this.getDemandeValue(skill);
        int max = iniNum;
        for (int i = 0; i < max; i++) {
            if (num > 0)
                stars.append("¡ï");
            else if (iniNum > 0)
                stars.append("¡î");
            iniNum--;
            num--;
        }
        return stars.toString();
    }
}

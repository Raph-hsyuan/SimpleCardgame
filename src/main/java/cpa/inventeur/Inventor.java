package cpa.inventeur;

import java.util.EnumMap;
import static cpa.inventeur.Skill.*;

/**
 * @author HUANG Shenyuan
 * @author Wu Kejia
 * @date 2018-4-13
 */
public enum Inventor {
    NEWTON("newton", 2, 0, 1, 1), EDISON("edison", 2, 2, 2, 0);

    private final String name;
    private boolean state;
    private static final boolean FREE = true;
    private static final boolean BUSY = false;
    private final EnumMap<Skill, Integer> skills = new EnumMap<>(Skill.class);
    private final EnumMap<Skill, Integer> iniSkills = new EnumMap<>(Skill.class);

    private Inventor(String name, int phy, int che, int mac, int mat) {
        this.name = name;
        state = FREE;
        skills.put(PHYSICS, phy);
        skills.put(CHEMISTRY, che);
        skills.put(MACHINERY, mac);
        skills.put(MATHS, mat);
        iniSkills.putAll(skills);

    }

    void setFree() {
        this.state = FREE;
    }

    void setBusy() {
        this.state = BUSY;
    }

    boolean isFree() {
        return state;
    }

    @Override
    public String toString() {
        return this.name;
    }

    void initial() {
        skills.putAll(iniSkills);
        state = FREE;
    }
    
    int getSkillValue(Skill skill) {
        return skills.get(skill);
    }

}

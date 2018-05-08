package cpa.inventeur;

import java.util.EnumMap;
import static cpa.inventeur.Skill.*;
import static cpa.inventeur.PlayerColor.*;

/**
 * @author HUANG Shenyuan
 * @author Wu Kejia
 * @date 2018-4-13
 */
public enum Inventor {
    CURIE("curie",RED, 1, 1, 0, 0), EDISON("edison",RED, 1, 0, 0, 1), EINSTEIN("einstein",RED, 1, 0, 0, 1), TESLA("tesl",RED, 0, 0, 2, 0),//RED
    PASCAL("pascal",YELLOW, 0, 0, 1, 1), BOWLE("bowle",YELLOW, 0, 2, 0, 0), NEWTON("newton",YELLOW, 1, 0, 1, 0), GALILEI("galilei",YELLOW, 1, 1, 0, 0), //YELLOW
    LOVELACE("lovelace",PURPLE, 0, 0, 1, 1), WATT("watt",PURPLE, 0, 0, 2, 0), LAVOISIER("lavoisier",PURPLE, 0, 2, 0, 0), FRANKLIN("franklin",PURPLE, 1, 0, 1, 0), //PURPLE
    GUIENBERG("guienberg",BLUE, 0, 0, 2, 0), FIBONACCI("fibonacci",BLUE, 0, 0, 0, 2), ORESME("oresme",BLUE, 1, 0, 0, 1), BINGEN("bingen",BLUE, 1, 1, 0, 0),//BLUE
    ARISTOTELES("aristoteles",GREEN, 1, 1, 0, 0), HYPPOKRATES("hyppokrates",GREEN, 0, 2, 0, 0),HYPATIA("hypatia",GREEN, 1, 0, 0, 1), ARCHIMEDES("archimedes",GREEN, 1, 0, 1, 0);//GREEN 
    

    private final String name;
    private boolean state;
    private static final boolean FREE = true;
    private static final boolean BUSY = false;
    private final EnumMap<Skill, Integer> skills = new EnumMap<>(Skill.class);
    private final EnumMap<Skill, Integer> iniSkills = new EnumMap<>(Skill.class);
    private final PlayerColor color;
    private Inventor(String name, PlayerColor color, int phy, int che, int mac, int mat) {
        this.name = name;
        this.color = color;
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

    public String toCard() {
        StringBuilder inventor = new StringBuilder();
        inventor.append("\n\t|--------|\n\t|>" + name + "<|\n");
        if (this.isFree())
            inventor.append("\t|- FREE -|\n");
        else
            inventor.append("\t|-OCCUPE-|\n");
        inventor.append("\t|--------|\n");
        for (Skill skill : skills.keySet()) {
            inventor.append("\t|-" + skill + toStars(skill) + "\n");
        }
        return inventor.toString();
    }

    String toStars(Skill skill) {
        StringBuilder stars = new StringBuilder();
        int num = this.getSkillValue(skill);
        for (int i = 0; i < num; i++)
            stars.append("★");
        return stars.toString();
    }
    
    PlayerColor getColor() {
        return this.color;
    }
}

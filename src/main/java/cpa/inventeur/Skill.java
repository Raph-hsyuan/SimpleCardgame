package cpa.inventeur;

/**
 * @author Liu Jiaqi
 * @date 2018-4-12
 */
public enum Skill {
    PHYSICS("PHY"), CHEMISTRY("CHE"), MACHINERY("MAC"), MATHS("MAT");
    private String name;

    Skill(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

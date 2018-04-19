package cpa.inventeur;

/**
 * @author HUANG Shenyuan
 * @date 2018-4-19
 */
public enum PlayerColor {
    RED("red"), BLUE("blue"), YELLOW("yellow"), GREEN("green"), PURPLE("purple");
    String name;

    PlayerColor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

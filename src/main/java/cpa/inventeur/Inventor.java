package cpa.inventeur;

/**
 * @author HUANG Shenyuan
 * @date 2018-3-29
 */
public class Inventor {
    private String name;
    private boolean state;
    private static final boolean FREE = true;
    private static final boolean BUSY = false;

    Inventor(String name) {
        this.name = name;
        state = FREE;
    }

    void setFree() {
        this.state = FREE;
    }

    void setBusy() {
        this.state = BUSY;
    }

    boolean getState() {
        return state;
    }

    @Override
    public String toString() {
        return this.name;
    }

}

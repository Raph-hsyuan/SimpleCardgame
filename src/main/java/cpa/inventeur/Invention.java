package cpa.inventeur;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wu Kejia
 * @date 2018-4-01
 */
public class Invention {
    private static final boolean FINISHED = true;
    private static final boolean INPROGRESS = false;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;
    private String name;
    private boolean state;
    List<Inventor> inventors = new ArrayList<>();

    Invention(String name) {
        this.name = name;
        state = INPROGRESS;
    }

    boolean addInventor(Inventor inventor) {
        if (inventors.isEmpty()) {
            inventors.add(inventor);
            return SUCCESS;
        }
        return FAILED;
    }

    boolean getState() {
        if (!inventors.isEmpty())
            state = FINISHED;
        return state;
    }

    @Override
    public String toString() {
        return name;
    }
}

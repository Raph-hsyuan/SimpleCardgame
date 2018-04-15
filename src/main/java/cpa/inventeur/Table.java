package cpa.inventeur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Liu Jiaqi
 * @date 2018-4-02
 */
public class Table {

    private static Table instance;
    private static final int SIZEOFTABLE = 4;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;
    private List<Invention> inventions;

    private Table() {
        inventions = new ArrayList<>();
    }

    public static Table getInstance() {
        if (instance == null)
            instance = new Table();
        return instance;
    }

    boolean putInvention(Invention invention) {
        if (inventions.size() < SIZEOFTABLE) {
            inventions.add(invention);
            return SUCCESS;
        }
        return FAILED;
    }

    List<Invention> getInventions() {
        return inventions;
    }

    void removeAll() {
        inventions.clear();
    }

    void removeFinished() {
        Iterator<Invention> it = inventions.iterator();
        while (it.hasNext()) {
            Invention x = it.next();
            if (x.isFinished()) {
                it.remove();
            }
        }
    }

    void printTable() {
        System.out.println("\nTable : ");
        for (Invention find : inventions) {
            System.out.println(find.toCard());
        }
    }
}

package cpa.inventeur;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HUANG Shenyuan
 * @date 2018-4-06
 */
public class PlayerConsole {
    private List<Inventor> inventors;
    private Table table = Table.getInstance();
    private int score = 0;

    PlayerConsole(List<Inventor> inventors) {
        this.inventors = inventors;
    }

    /**
     * @param inventor
     * @param invention
     *            Send a inventor to an invention
     */
    void send(Inventor inventor, Invention invention) {
        List<Invention> inventions = table.getInventions();
        boolean con1 = inventors.contains(inventor);
        boolean con2 = inventions.contains(invention);
        boolean con3 = !invention.isFinished();
        boolean con4 = inventor.isFree();
        if (con1 && con2 && con3 && con4) {
            invention.addInventor(inventor);
            inventor.setBusy();
        } else
            throw new RuntimeException("Send ERROR!"+con1+con2+con3+con4);
    }

    /**
     * Set ones' all inventors free
     */
    void setAllFree() {
        for (Inventor find : inventors)
            find.setFree();
    }

    /**
     * @return All the inventors
     */
    List<Inventor> getInventors() {
        return inventors;
    }

    /**
     * @return All the inventors who isn't occupied
     */
    List<Inventor> getLibres() {
        List<Inventor> libres = new ArrayList<>();
        for (Inventor find : inventors)
            if (find.isFree())
                libres.add(find);
        return libres;
    }

    void addPoint() {
        score++;
    }
    
    int getScore() {
        return score;
    }
}

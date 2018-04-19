package cpa.inventeur;

import static cpa.inventeur.Inventor.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HUANG Shenyuan
 * @author Wu Kejia
 * @date 2018-4-06
 */
public class PlayerConsole {
    private List<Inventor> inventors;
    private Table table = Table.getInstance();
    private int score = 0;
    PlayerColor color;

    PlayerConsole(PlayerColor color) {
        this.color = color;
        switch (color) {
        case RED:
            this.inventors = toInventorList(EDISON, EINSTEIN);
            break;
        case YELLOW:
            this.inventors = toInventorList(NEWTON, GALILEI);
            break;
        case PURPLE:
            this.inventors = toInventorList(WATT, FRANKLIN);
            break;
        case BLUE:
            this.inventors = toInventorList(ORESME, FIBONACCI);
            break;
        case GREEN:
            this.inventors = toInventorList(HYPATIA, HYPPOKRATES);
            break;
        }
    }

    /**
     * @param inventor
     * @param invention
     *            Send a inventor to an invention
     */
    boolean send(Inventor inventor, Invention invention) {
        List<Invention> inventions = table.getInventions();
        boolean con1 = inventors.contains(inventor);
        boolean con2 = inventions.contains(invention);
        boolean con3 = !invention.isFinished();
        boolean con4 = inventor.isFree();
        if (con1 && con2 && con3 && con4 ) {
            try{
                invention.addInventor(inventor);
            }catch(RuntimeException e){
                return false;
            }
            inventor.setBusy();
            return true;
        }
        else
            return false;
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

    StringBuilder printHand() {
        StringBuilder hand = new StringBuilder();
        hand.append("\nHand " + color + ":");
        for (Inventor find : inventors)
            hand.append(find.toCard());
        return hand;
    }

    List<Inventor> toInventorList(Inventor inventor1, Inventor inventor2) {
        List<Inventor> inventorList = new ArrayList<>();
        inventorList.add(inventor1);
        inventorList.add(inventor2);
        return inventorList;
    }

    void initialInventors() {
        for (Inventor inventor : inventors)
            inventor.initial();
    }
}

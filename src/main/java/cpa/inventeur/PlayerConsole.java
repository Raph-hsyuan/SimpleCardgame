package cpa.inventeur;

import static cpa.inventeur.Inventor.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author HUANG Shenyuan
 * @author Wu Kejia
 * @date 2018-4-06
 */
public class PlayerConsole {
    private List<Inventor> inventors;
    private Table table = Table.getInstance();
    PlayerColor color;
    private boolean canDoSth = true;
    EnumMap<Ticket, Integer> tickets = new EnumMap<>(Ticket.class);
    private boolean finishSth = false;
    private int addPoint = 0;
    private Invention ifinish = null;

    PlayerConsole(PlayerColor color) {
        for (Ticket t : Ticket.values())
            tickets.put(t, 0);
        this.color = color;
        switch (color) {
        case RED:
            this.inventors = toInventorList(CURIE, EDISON, EINSTEIN, TESLA);
            break;
        case YELLOW:
            this.inventors = toInventorList(PASCAL, BOWLE, NEWTON, GALILEI);
            break;
        case PURPLE:
            this.inventors = toInventorList(LOVELACE, WATT, LAVOISIER, FRANKLIN);
            break;
        case BLUE:
            this.inventors = toInventorList(GUIENBERG, FIBONACCI, ORESME, BINGEN);
            break;
        case GREEN:
            this.inventors = toInventorList(ARISTOTELES, HYPPOKRATES, HYPATIA, ARCHIMEDES);
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
        if (con1 && con2 && con3 && con4 && canDoSth) {
            if (!invention.addInventor(inventor))
                return false;
            inventor.setBusy();
            canDoSth = false;
            if (invention.isFinished()) {
                finishSth = true;
                addPoint++;
                ifinish = invention;
            }
            return true;
        } else
            return false;
    }

    /**
     * Set ones' all inventors free
     */
    boolean setAllFree() {
        if (canDoSth) {
            for (Inventor find : inventors)
                find.setFree();
            canDoSth = false;
            return true;
        }
        return false;
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

    StringBuilder printHand() {
        StringBuilder hand = new StringBuilder();
        hand.append("\nHand " + color + ":");
        for (Inventor find : inventors)
            hand.append(find.toCard());
        return hand;
    }

    List<Inventor> toInventorList(Inventor inventor1, Inventor inventor2, Inventor inventor3, Inventor inventor4) {
        List<Inventor> inventorList = new ArrayList<>();
        inventorList.add(inventor1);
        inventorList.add(inventor2);
        inventorList.add(inventor3);
        inventorList.add(inventor4);
        return inventorList;
    }

    void initialInventors() {
        for (Inventor inventor : inventors)
            inventor.initial();
    }

    void setNewTurn() {
        canDoSth = true;
    }

    /**
     * @param ticket
     * @return true if use the ticket successfully(exist)
     */
    boolean useTicket(Ticket ticket) {
        if (tickets.get(ticket) <= 0)
            return false;
        switch (ticket) {
        case ADDONEPOINT:
            this.addPoint++;
            break;
        case SETALLFREE:
            this.setAllFree();
            break;
        }
        tickets.replace(ticket, tickets.get(ticket) - 1);
        return true;
    }

    /**
     * @param ticket
     *            Add a ticket to console
     */
    boolean pickTicket(Invention invention, Ticket ticket) {
        if (invention.removeTicket(ticket)) {
            tickets.replace(ticket, tickets.get(ticket) + 1);
            if (ticket.equals(Ticket.ADDONEPOINT))
                useTicket(Ticket.ADDONEPOINT);
            return true;
        }
        return false;
    }

    /**
     * @return All the tickets in console
     */
    List<Ticket> getTicket() {
        List<Ticket> hasTicket = new ArrayList<>();
        for (Entry<Ticket, Integer> t : tickets.entrySet())
            for (int i = 0; i < t.getValue(); i++)
                hasTicket.add(t.getKey());
        return hasTicket;
    }

    /**
     * @return what i have just finished
     */
    Invention getiFinish() {
        Invention inv;
        if (finishSth) {
            inv = ifinish;
            finishSth = false;
            return inv;
        }
        throw new IllegalStateException("Nothing Finished");
    }

    /**
     * @return get the number of point should be added
     */
    int getAddPoint() {
        int point = addPoint;
        addPoint = 0;
        return point;
    }

    /**
     * @return
     */
    boolean finishSth() {
        return finishSth;
    }
}

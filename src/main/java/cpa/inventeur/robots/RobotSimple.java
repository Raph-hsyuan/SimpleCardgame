package cpa.inventeur.robots;

import java.util.List;
import java.util.Random;

import cpa.inventeur.cardsenums.Invention;
import cpa.inventeur.cardsenums.Inventor;
import cpa.inventeur.cardsenums.Ticket;
import cpa.inventeur.controller.PlayerConsole;
import cpa.inventeur.controller.Table;

import static java.util.logging.Level.*;

/**
 * @author Liu Jiaqi
 * @date 2018-4-02
 */
public class RobotSimple extends Robot {

    private static final int TRYTIMES = 10;
    private Random ran = new Random();

    public RobotSimple(String name, PlayerConsole console) {
        table = Table.getInstance();
        this.console = console;
        this.name = name;
    }

    @Override
    public void toPlay() {
        List<Inventor> libre;
        libre = console.getLibres();
        boolean st = false;
        Invention mark = table.getNotFinished().get(0);
        if (libre.isEmpty()) {
            setAllFree();
        } else {
            List<Invention> notFinished = table.getNotFinished();
            for (int i = 0; i < TRYTIMES; i++) {
                int thing = ran.nextInt(notFinished.size());
                int who = ran.nextInt(libre.size());
                if (send(libre.get(who), notFinished.get(thing))) {
                    mark = notFinished.get(thing);
                    st = true;
                    break;
                }
            }

            if (!st)
                setAllFree();
            else if (mark.isFinished()) {
                iFinish(mark);
            }
        }

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void chooseTicket(Invention inv) {
        int index = ran.nextInt(inv.getTicket().size());
        Ticket ti = inv.getTicket().get(index);
        while(!console.pickTicket(inv, ti)) {
            index = ran.nextInt(Ticket.values().length);
            ti = inv.getTicket().get(index);
        }
        StringBuilder choose = new StringBuilder();
        choose.append(this+"chose"+ti);
        LOG.log(INFO, "#{0}", choose);
    }
    
}

package cpa.inventeur;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import static java.util.logging.Level.*;

/**
 * @author Liu Jiaqi
 * @date 2018-4-02
 */
public class RobotSimple implements Robot {
    private Table table;
    private String name;
    private PlayerConsole console;
    private static final Logger LOG = Logger.getLogger("RobotInfo");
    private static final int TRYTIMES = 10;
    private Random ran = new Random();

    RobotSimple(String name, PlayerConsole console) {
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

    private boolean send(Inventor inventor, Invention invention) {
        if(console.send(inventor, invention)) {
            StringBuilder done = new StringBuilder();
            done.append("# " + this + " sends " + inventor + "---->" + invention);
            LOG.log(INFO, "\nRobotInfo:\n{0}\n\n", done);
            return true;
        }
        return false;
    }

    private void iFinish(Invention in) {
        StringBuilder fi = new StringBuilder();
        fi.append(this + " finishs " + in);
        LOG.log(INFO, "\ngetPoint :{0}\n",fi);
    }

    private void setAllFree() {
        console.setAllFree();
        LOG.log(INFO, "#{0} set All Free\n\n", this);
    }

    @Override
    public void closeLogger() {
        LOG.setLevel(OFF);
    }
    
    @Override
    public PlayerColor getColor() {
        return console.color;
    }
    
    @Override
    public PlayerConsole getConsole() {
        return console;
    }

    @Override
    public void chooseTicket(Invention inv) {
        // TODO Auto-generated method stub
        
    }

}

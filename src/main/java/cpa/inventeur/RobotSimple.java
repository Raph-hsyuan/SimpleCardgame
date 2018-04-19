package cpa.inventeur;

import java.util.List;
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
    RobotSimple(String name, PlayerConsole console) {
        table = Table.getInstance();
        this.console = console;
        this.name = name;
    }

    @Override
    public void toPlay() {
        List<Inventor> libre;
        libre = console.getLibres();
        if (libre.isEmpty()) {
            setAllFree();
        } else {
            send(libre.get(0), table.getInventions().get(0));
            if (table.getInventions().get(0).isFinished()) {
                addPoint();
            }
        }
    }

    @Override
    public int getScore() {
        return console.getScore();
    }

    @Override
    public String toString() {
        return name;
    }

    private void send(Inventor inventor, Invention invention) {
        console.send(inventor, invention);
        StringBuilder done = new StringBuilder();
        done.append("# " + this + " sends " + inventor + "---->" + invention);
        LOG.log(INFO, "\nRobotInfo:\n{0}\n\n", done);
    }

    private void addPoint() {
        console.addPoint();
        LOG.log(INFO, "#{0} gets one point!!\n\n", this);
    }

    private void setAllFree() {
        console.setAllFree();
        LOG.log(INFO, "#{0} set All Free\n\n", this);
    }
    
    @Override
    public void closeLogger() {
        LOG.setLevel(OFF);
    }
    
}

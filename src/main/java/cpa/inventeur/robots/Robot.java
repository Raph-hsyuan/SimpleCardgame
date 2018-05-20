package cpa.inventeur.robots;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.OFF;

import java.util.logging.Logger;

import cpa.inventeur.cardsenums.Invention;
import cpa.inventeur.cardsenums.Inventor;
import cpa.inventeur.cardsenums.PlayerColor;
import cpa.inventeur.cardsenums.Ticket;
import cpa.inventeur.controller.PlayerConsole;
import cpa.inventeur.controller.Table;

/**
 * @author 
 * @date 2018-4-06
 */
public abstract class Robot {
    protected Table table;
    protected String name;
    protected PlayerConsole console;
    protected static final Logger LOG = Logger.getLogger("RobotInfo");
    
    public abstract void toPlay();
    
    public abstract void chooseTicket(Invention inv);
    
    void closeLogger() {
        LOG.setLevel(OFF);
    }

    public PlayerColor getColor() {
        return console.color;
    }
    
    public PlayerConsole getConsole() {
        return console;
    }
    
    boolean useTicket(Ticket tik) {
        StringBuilder use = new StringBuilder();
        use.append(this + " use " + tik);
        LOG.log(INFO, "#{0}", use);
        return console.useTicket(tik);
    }
    
    boolean send(Inventor inventor, Invention invention) {
        if(console.send(inventor, invention)) {
            StringBuilder done = new StringBuilder();
            done.append("# " + this + " sends " + inventor + "---->" + invention);
            LOG.log(INFO, "\nRobotInfo:\n{0}\n\n", done);
            return true;
        }
        return false;
    }
    
    void iFinish(Invention in) {
        StringBuilder fi = new StringBuilder();
        fi.append(this + " finishs " + in);
        LOG.log(INFO, "\ngetPoint :{0}\n", fi);
    }
    
    void setAllFree() {
        console.setAllFree();
        LOG.log(INFO, "#{0} set All Free\n\n", this);
    }
    
}

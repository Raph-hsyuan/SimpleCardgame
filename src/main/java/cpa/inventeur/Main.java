package cpa.inventeur;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import static java.util.logging.Level.*;
import static cpa.inventeur.Level.*;

public class Main {
    private static final Logger LOG1 = Logger.getLogger("GameInfo");
    private static final Logger LOG2 = Logger.getLogger("RobotInfo");
    private static final Logger LOG3 = Logger.getLogger("CountInfo");
    private static final int SAMPLESIZE = 10000;
    static Random s = new Random();
    public static void main(String[] args) {
        Map<String, Level> builder = new HashMap<>();
        builder.put("huang", SIMPLE);
        builder.put("wu", SIMPLE);
        builder.put("liu", SIMPLE);
        builder.put("yu", SIMPLE);
        builder.put("super", NORMAL);
        GameEngine mygame = new GameEngine(builder);
        mygame.gameStart();
        countWinRate();
    }

    static void countWinRate() {
        LOG1.setLevel(OFF);
        LOG2.setLevel(OFF);
        int winTimes1 = winTimes(1);
        LOG3.log(INFO, "WinRate of 1 Normal vs 1 Simple is {0}%", winTimes1*100.0/SAMPLESIZE);
        int winTimes2 = winTimes(2);
        LOG3.log(INFO, "WinRate of 1 Normal vs 2 Simple is {0}%", winTimes2*100.0/SAMPLESIZE);
        int winTimes3 = winTimes(3);
        LOG3.log(INFO, "WinRate of 1 Normal vs 3 Simple is {0}%", winTimes3*100.0/SAMPLESIZE);
        int winTimes4 = winTimes(4);
        LOG3.log(INFO, "WinRate of 1 Normal vs 4 Simple is {0}%\n\n\nFINISH", winTimes4*100.0/SAMPLESIZE);
            
    }

    static int winTimes(int simple) {
        int winTimes = 0;
        for(int i = 0;i<SAMPLESIZE;i++)
            if(winN1VsS(simple))
                winTimes++;
        return winTimes;
    }
    
    static boolean winN1VsS(int simple) {
        GameEngine mygame = new GameEngine(normal1VsSimple(simple));
        mygame.initialGame();
        mygame.gameStart();
        for(PlayerColor find : mygame.getWinner()) {
            if( mygame.getRobot(find) instanceof RobotNormal)
                return true;
        }
        return false;
    }
    
    static Map<String,Level> normal1VsSimple(int simple){
        Map<String, Level> builder = new HashMap<>();
        builder.put("normal", NORMAL);
        for(int i = 0;i<simple;i++)
            builder.put("simple"+i, SIMPLE);
        return builder;
    }
}

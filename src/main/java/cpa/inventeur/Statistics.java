package cpa.inventeur;

import static cpa.inventeur.Level.NORMAL;
import static cpa.inventeur.Level.SIMPLE;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.OFF;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class Statistics {
    private static final Logger LOG1 = Logger.getLogger("GameInfo");
    private static final Logger LOG2 = Logger.getLogger("RobotInfo");
    private static final Logger LOG3 = Logger.getLogger("CountInfo");
    private static final int SAMPLESIZE = 10000;
    static Random s = new Random();
    public static void main(String[] args) {
        LOG3.log(INFO, "\n\nLaunch the game for {0} times and count the Win Rate in different case.", SAMPLESIZE);
        countWinRate();

    }
    private static void countWinRate() {
        LOG1.setLevel(OFF);
        LOG2.setLevel(OFF);
        int winTimes1 = winTimes(1);
        LOG3.log(INFO, "\n\nWinRate of 1 Normal vs 1 Simple is {0}%\n", winTimes1 * 100.0 / SAMPLESIZE);
        int winTimes2 = winTimes(2);
        LOG3.log(INFO, "\n\nWinRate of 1 Normal vs 2 Simple is {0}%\n", winTimes2 * 100.0 / SAMPLESIZE);
        int winTimes3 = winTimes(3);
        LOG3.log(INFO, "\n\nWinRate of 1 Normal vs 3 Simple is {0}%\n", winTimes3 * 100.0 / SAMPLESIZE);
        int winTimes4 = winTimes(4);
        LOG3.log(INFO, "\n\nWinRate of 1 Normal vs 4 Simple is {0}%\n\n\nFINISH", winTimes4 * 100.0 / SAMPLESIZE);

    }

    private static int winTimes(int simple) {
        int winTimes = 0;
        for (int i = 0; i < SAMPLESIZE; i++)
            if (winN1VsS(simple))
                winTimes++;
        return winTimes;
    }

    private static boolean winN1VsS(int simple) {
        GameEngine mygame = new GameEngine(normal1VsSimple(simple));
        mygame.initialGame();
        mygame.gameStart();
        for (PlayerColor find : mygame.getWinner()) {
            if (mygame.getRobot(find) instanceof RobotNormal)
                return true;
        }
        return false;
    }

    private static Map<String, Level> normal1VsSimple(int simple) {
        Map<String, Level> builder = new HashMap<>();
        builder.put("normal", NORMAL);
        for (int i = 0; i < simple; i++)
            builder.put("simple" + i, SIMPLE);
        return builder;
    }
}

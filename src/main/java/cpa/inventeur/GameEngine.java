package cpa.inventeur;

import java.util.logging.Logger;
import static java.util.logging.Level.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static cpa.inventeur.Invention.*;

/**
 * @author HUANG Shenyuan
 * @author WU Kejia
 * @date 2018-4-04
 */
public class GameEngine {
    private Robot player;
    private Table gameTable = Table.getInstance();
    private static final Logger LOG = Logger.getLogger("GameInfo");
    private List<Robot> players = new ArrayList<>();
    private List<PlayerConsole> consoles = new ArrayList<>();
    GameEngine(Map<String, Level> build) {
        if (build.size() > 5)
            throw new RuntimeException("Max 5 players");
        PlayerColor[] colors = PlayerColor.values();
        int i = 0;
        for (Entry<String, Level> entry : build.entrySet()) {
            PlayerConsole console = new PlayerConsole(colors[i++]);
            Level level = entry.getValue();
            String name = entry.getKey();
            switch (level) {
            case NORMAL:
                player = new RobotNormal(name, console);
                break;
            case SIMPLE:
                player = new RobotSimple(name, console);
                break;
            default:
                player = new RobotSimple(name, console);
                break;
            }
            players.add(player);
        }

    }

    /**
     * Launch the game
     */
    void gameStart() {
        putInventions();
        int round = 1;
        while (notFinished()) {
            printRoundStart(round);
            playerAction();
            printRoundFinish(round);
            removeFinished();
            round++;
        }
        printFinish();
    }

    /**
     * Print GameOver
     */
    void printFinish() {
        LOG.log(INFO, "\n***************\n*GAME FINISHED*\n***************\n{0}", getScore());
    }

    /**
     * Print the final result
     */
    StringBuilder getScore() {
        StringBuilder score = new StringBuilder();
        score.append("|PLAYER\t|SCORE\n");
        for(Robot p : players)
            score.append("\n|" + p + "\t|" + p.getScore());
        return score;
    }

    /**
     * Put All Inventions on table
     */
    void putInventions() {
        gameTable.putInvention(CAR);
        gameTable.putInvention(PLANE);
        gameTable.putInvention(BOAT);
        gameTable.putInvention(BIKE);
    }

    /**
     * @return true if game is not finished
     */
    boolean notFinished() {
        return !gameTable.getNotFinished().isEmpty();
    }

    void removeFinished() {
        gameTable.removeFinished();
    }

    void printRoundStart(int round) {
        StringBuilder start = new StringBuilder();
        start.append("Round " + round + " :");
        for(PlayerConsole console : consoles)
            start.append(console.printHand());
        start.append(gameTable.printTable());
        LOG.log(INFO, "\nA new Round Start\n{0}", start);
    }

    void printRoundFinish(int round) {
        StringBuilder finish = new StringBuilder();
        finish.append("Round " + round + " End");
        for(PlayerConsole console : consoles)
            finish.append(console.printHand());
        finish.append(gameTable.printTable());
        finish.append("\n" + getScore());
        finish.append("\n\n\n\n#####################\n#####################\n\n");
        LOG.log(INFO, "\nA Round Finish\n{0}", finish);
    }

    void playerAction() {
        for(Robot robot : players)
            if(notFinished())
                robot.toPlay();
            else break;
    }
}

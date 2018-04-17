package cpa.inventeur;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Level.*;
import static cpa.inventeur.Inventor.*;
import static cpa.inventeur.Invention.*;

/**
 * @author HUANG Shenyuan
 * @date 2018-4-04
 */
public class GameEngine {
    private Robot player1;
    private Table gameTable = Table.getInstance();
    PlayerConsole console1;
    private static final Logger LOG = Logger.getLogger("GameInfo");

    GameEngine(String robot) {
        console1 = new PlayerConsole(toInventorList(NEWTON, EDISON));
        switch (robot) {
        case "NORMAL":
            player1 = new RobotNormal("Liu1", console1);
            break;
        case "SIMPLE":
            player1 = new RobotSimple("huang1", console1);
            break;
        default:
            player1 = new RobotSimple("huang1", console1);
            break;
        }

    }

    List<Inventor> toInventorList(Inventor inventor1, Inventor inventor2) {
        List<Inventor> inventors = new ArrayList<>();
        inventors.add(inventor1);
        inventors.add(inventor2);
        return inventors;
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
        score.append("|" + player1 + "\t|" + player1.getScore());
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
        return !gameTable.getInventions().isEmpty();
    }

    void removeFinished() {
        gameTable.removeFinished();
    }

    void printRoundStart(int round) {
        StringBuilder start = new StringBuilder();
        start.append("Round " + round + " :");
        start.append(console1.printHand());
        start.append(gameTable.printTable());
        LOG.log(INFO, "\nA new Round Start\n{0}", start);
    }

    void printRoundFinish(int round) {
        StringBuilder finish = new StringBuilder();
        finish.append("Round " + round + " End");
        finish.append(console1.printHand());
        finish.append(gameTable.printTable());
        finish.append("\n" + getScore());
        finish.append("\n\n\n\n#####################\n#####################\n\n");
        LOG.log(INFO, "\nA Round Finish\n{0}", finish);
    }

    void playerAction() {
        player1.toPlay();
    }
}

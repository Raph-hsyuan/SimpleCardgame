package cpa.inventeur.controller;

import java.util.logging.Logger;

import cpa.inventeur.cardsenums.Invention;
import cpa.inventeur.cardsenums.Inventor;
import cpa.inventeur.cardsenums.Level;
import cpa.inventeur.cardsenums.PlayerColor;
import cpa.inventeur.cardsenums.Ticket;
import cpa.inventeur.robots.Robot;
import cpa.inventeur.robots.RobotNormal;
import cpa.inventeur.robots.RobotSimple;

import static java.util.logging.Level.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

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
    private Map<PlayerColor, Integer> scoreBoard = new EnumMap<>(PlayerColor.class);
    private int round = 1;
    public GameEngine(Map<String, Level> build) {
        if (build.size() > 5)
            throw new IllegalStateException("Max 5 players");
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
            consoles.add(console);
            for (PlayerConsole con : consoles) {
                scoreBoard.put(con.color, 0);
            }
        }
    }

    /**
     * Launch the game
     */
    public void gameStart() {
        putInventions();
        addTickets();
        round = 1;
        while (notFinished()) {
            printRoundStart();
            playerAction();
            printRoundFinish();
            removeFinished();
            round++;
            setNewTurn();

        }
        printFinish();
    }

    /**
     * Print GameOver
     */
    private void printFinish() {
        LOG.log(INFO, "\n***************\n*GAME FINISHED*\n***************\n{0}", getScore());
        StringBuilder winner = new StringBuilder();
        for(PlayerColor color : getWinner())
            winner.append(" "+getRobot(color));
        LOG.log(INFO, "\n{0} is Winner!!", winner);
        LOG.log(INFO, "\nGame finishs successfully after {0} round\n 0 invention remains", round);
    }

    /**
     * Print the final result
     */
    private StringBuilder getScore() {
        StringBuilder score = new StringBuilder();
        score.append("|PLAYER Name\t|Color\t|SCORE\n");
        for (Robot p : players)
            score.append("\n|" + p + "\t|" + p.getColor() + "\t|" + scoreBoard.get(p.getColor()));
        return score;
    }

    /**
     * Put All Inventions on table
     */
    void putInventions() {
        int num = players.size() + 3;
        Random ran = new Random();
        List<Invention> inv = new ArrayList<>();
        for (Invention find : Invention.values())
            if (find.ordinal() > 4)
                inv.add(find);
        while (num > 1) {
            int mark = ran.nextInt(num);
            gameTable.putInvention(inv.get(mark));
            inv.remove(mark);
            num--;
        }
    }

    /**
     * @return true if game is not finished
     */
    private boolean notFinished() {
        return !gameTable.getNotFinished().isEmpty();
    }

    private void removeFinished() {
        gameTable.removeFinished();
    }

    private void printRoundStart() {
        StringBuilder start = new StringBuilder();
        start.append("Round " + round + " :");
        for (PlayerConsole console : consoles)
            start.append(console.printHand());
        start.append(gameTable.printTable());
        LOG.log(INFO, "\nA new Round Start\n{0}", start);
    }

    private void printRoundFinish() {
        StringBuilder finish = new StringBuilder();
        finish.append("Round " + round + " End");
        for (PlayerConsole console : consoles)
            finish.append(console.printHand());
        finish.append(gameTable.printTable());
        finish.append("\n" + getScore());
        finish.append("\n\n\n\n#####################\n#####################\n\n");
        LOG.log(INFO, "\nA Round Finish\n{0}", finish);
    }

    private void playerAction() {
        for (Robot robot : players) {
            PlayerColor color = robot.getColor();
            PlayerConsole console = robot.getConsole();
            if (notFinished())
                robot.toPlay();
            else
                break;
            int add = console.getAddPoint();
            if (console.finishSth())
                chooseTicket(console.getiFinish());
            updateScore(color, add);
        }
    }

    void chooseTicket(Invention inv) {
        List<PlayerColor> rank = new ArrayList<>();
        for (PlayerColor co : inv.getContributers().keySet())
            rank.add(co);
        for (int i = 0; i < rank.size() - 1; i++)
            for (int j = 0; j < rank.size() - 1 - i; j++) {
                if (inv.getContribute(rank.get(j)) < inv.getContribute(rank.get(j + 1)))
                    Collections.swap(rank, j, j + 1);
            }
        for (PlayerColor co : rank) {
            if (!inv.getTicket().isEmpty())
                getRobot(co).chooseTicket(inv);
            else
                break;
        }

    }

    void updateScore(PlayerColor color, int add) {
        int score = scoreBoard.get(color);
        scoreBoard.replace(color, score + add);
    }

    public void initialGame() {
        gameTable.initialInventions();
        for (Inventor inventor : Inventor.values())
            inventor.initial();
    }

    public List<PlayerColor> getWinner() {
        int max = 0;
        List<PlayerColor> winner = new ArrayList<>();
        for (Integer i : scoreBoard.values()) {
            if (max < i) {
                max = i;
            }
        }
        for (Entry<PlayerColor, Integer> entry : scoreBoard.entrySet()) {
            if (max == entry.getValue())
                winner.add(entry.getKey());
        }
        return winner;
    }

    void setNewTurn() {
        for (PlayerConsole console : consoles)
            console.setNewTurn();
    }

    void addTickets() {
        Random ran = new Random();
        for (Invention inv : gameTable.getInventions()) {
            for (Ticket tic : Ticket.values())
                if (ran.nextBoolean())
                    inv.addTicket(tic);
        }
    }

    public Robot getRobot(PlayerColor color) {
        for (Robot rob : players)
            if (rob.getColor().equals(color))
                return rob;
        throw new IllegalStateException("this Color not exist");
    }
}

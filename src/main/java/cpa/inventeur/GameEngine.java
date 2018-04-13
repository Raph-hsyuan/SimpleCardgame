package cpa.inventeur;

import java.util.ArrayList;
import java.util.List;
import static cpa.inventeur.Inventor.*;
/**
 * @author HUANG Shenyuan
 * @date 2018-4-04
 */
public class GameEngine {
    private RobotSimple player1;
    private Table gameTable = Table.getInstance();
    private Invention car = new Invention(" CAR  ");
    private Invention plane = new Invention("PLANE ");
    private Invention boat = new Invention(" BOAT ");
    private Invention bike = new Invention(" BIKE ");
    PlayerConsole console1;
    GameEngine() {
        console1 = new PlayerConsole(toInventorList(NEWTON,EDISON));
        player1 = new RobotSimple("huang1", console1);
    }

    List<Inventor> toInventorList(Inventor inventor1, Inventor inventor2){
        List<Inventor> inventors = new ArrayList<>();
        inventors.add(inventor1);
        inventors.add(inventor2);
        return inventors;
    }
    
    /**
     * Launch the game
     */
    void gameStart() {
        gameTable.putInvention(car);
        gameTable.putInvention(plane);
        gameTable.putInvention(boat);
        gameTable.putInvention(bike);
        int round = 1;
        while (!gameTable.getInventions().isEmpty()) {            
            System.out.println("Round "+round+" :");
            console1.printHand();
            gameTable.printTable();
            player1.toPlay();
            gameTable.printTable();
            System.out.println("Round "+round+" End");
            printScore();
            System.out.println("-\n-\n-\n-\n-\n-\n-\n-");
            gameTable.removeFinished();
            round++;
        }
        printFinish();
        printScore();
    }

    /**
     * Print GameOver
     */
    void printFinish() {
        System.out.println("\n\n\n***************");
        System.out.println("*GAME FINISHED*");
        System.out.println("***************");
    }

    /**
     * Print the final result
     */
    void printScore() {
        System.out.println("|PLAYER\t" + "|SCORE");
        System.out.println("|" + player1 + "\t|" + player1.getScore());
    }
}

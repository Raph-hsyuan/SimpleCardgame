package cpa.inventeur;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HUANG Shenyuan
 * @date 2018-4-04
 */
public class GameLauncher {
    private RobotSimple player1;
    private Table gameTable;
    private Inventor newton;
    private Inventor edison;
    private Invention car;
    
    GameLauncher() {
        car = new Invention("CAR");
        gameTable = Table.getInstance();
        newton = new Inventor("MY_INVENTOR");
        player1 = new RobotSimple("huang1", gameTable, toInventorList(newton,edison));
    }

    List<Inventor> toInventorList(Inventor inventor1, Inventor inventor2){
        List<Inventor> inventions = new ArrayList<>();
        inventions.add(inventor1);
        inventions.add(inventor2);
        return inventions;
    }
    
    /**
     * Launch the game
     */
    void gameStart() {
        gameTable.putInvention(car);
        while (gameTable.getInventions().isEmpty()) {
            player1.toPlay();
            for(Invention invention : gameTable.getInventions()) {
                if(invention.isFinished()) gameTable.removeInvention(invention);
            }
        }
        printFinish();
        printScore();
    }

    /**
     * Print GameOver
     */
    void printFinish() {
        System.out.println("\nGAME FINISHED");
    }

    /**
     * Print the final result
     */
    void printScore() {
        System.out.println("\n|PLAYER\t" + "|SCORE");
        System.out.println("|" + player1 + "\t|" + player1.getScore());
    }
 
}

package cpa.inventeur;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HUANG Shenyuan
 * @date 2018-4-04
 */
public class GameLauncher {
    private RobotSimple player1;
    private Table gameTable = Table.getInstance();
    private Inventor newton = new Inventor("Newton");
    private Inventor edison = new Inventor("Edison");
    private Invention car = new Invention("CAR");
    private Invention plane = new Invention("PLANE");
    private Invention boat = new Invention("BOAT");
    private Invention bike = new Invention("BIKE");
    
    GameLauncher() {
        PlayerConsole console1 = new PlayerConsole(toInventorList(newton,edison));
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
        while (!gameTable.getInventions().isEmpty()) {
            player1.toPlay();
            gameTable.removeFinished();
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

package cpa.inventeur;

/**
 * @author HUANG Shenyuan
 * @date 2018-4-04
 */
public class GameLauncher {
    private RobotSimple player1;
    private Table gameTable;
    private Inventor newton;
    private Invention car;
    GameLauncher() {
        car = new Invention("CAR");
        gameTable = Table.getInstance();
        newton = new Inventor("MY_INVENTOR");
        player1 = new RobotSimple("huang1", gameTable, newton);
    }

    /**
     * Launch the game
     */
    void gameStart() {
        gameTable.putInvention(car);
        while (!gameTable.getInvention().isFinished())
            player1.toPlay();
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

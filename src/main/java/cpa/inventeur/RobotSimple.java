package cpa.inventeur;

/**
 * @author Liu Jiaqi
 * @date 2018-4-02
 */
public class RobotSimple {
    private Table table;
    private Inventor inventor;
    private int score;
    private String name;

    RobotSimple(String name, Table table, Inventor inventor) {
        score = 0;
        this.table = table;
        this.inventor = inventor;
        this.name = name;
    }

    void toPlay() {
        Invention invention = table.getInvention();
        if (!invention.isFinished() && inventor.getState()) { // When invention is inprog and inventor is free
            invention.addInventor(inventor);
            System.out.print("Send [" + inventor + "] ------------> [" + invention + "]\n");
            inventor.setBusy();
            if (invention.isFinished())
                score++;
        } else
            System.out.print("NOTHING TO DO\n");
    }

    int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name;
    }
}

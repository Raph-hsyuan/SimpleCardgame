package cpa.inventeur;

/**
 * @author Liu Jiaqi
 * @date 2018-4-02
 */
public class RobotSimple {
    private Table table;
    private Inventor inventor;
    RobotSimple(Table table,Inventor inventor){
        this.table = table;
        this.inventor = inventor;
    }
    
    void toPlay() {
        Invention invention = table.getInvention();
        if(!invention.isFinished()&&inventor.getState()) {  //When invention is inprog and inventor is free
            invention.addInventor(inventor);
            inventor.setBusy();
        }else
            System.out.print("NOTHING TO DO\n");
    }
}

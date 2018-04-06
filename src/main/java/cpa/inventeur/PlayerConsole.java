package cpa.inventeur;

import java.util.List;

/**
 * @author HUANG Shenyuan
 * @date 2018-4-06
 */
public class PlayerConsole {
    private List<Inventor> inventors;
    private Table table = Table.getInstance();
    
    PlayerConsole(List<Inventor> inventors){
        this.inventors=inventors;
    }
    
    /**
     * @param inventor
     * @param invention
     * Send a inventor to an invention
     */
    void send(Inventor inventor,Invention invention){
        List<Invention> inventions = table.getInventions();
        boolean con1 = inventors.contains(inventor);
        boolean con2 = inventions.contains(invention);
        boolean con3 = !invention.isFinished();
        boolean con4 = inventor.isFree();
        if(con1&&con2&&con3&&con4) 
            invention.addInventor(inventor);
        else
            throw new RuntimeException("Send ERROR!");
    }
    
    /**
     * Set ones' all inventors free 
     */
    void setAllFree() {
        for(Inventor find : inventors)
            find.setFree();
    }
}

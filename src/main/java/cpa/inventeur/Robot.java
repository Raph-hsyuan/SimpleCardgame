package cpa.inventeur;

/**
 * @author 
 * @date 2018-4-06
 */
public interface Robot {
    void toPlay();
    
    void closeLogger();

    PlayerColor getColor();
    
    PlayerConsole getConsole();
    
    void chooseTicket(Invention inv);
}

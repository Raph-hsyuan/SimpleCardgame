package cpa.inventeur;

/**
 * @author 
 * @date 2018-4-06
 */
public interface Robot {
    void toPlay();

    int getScore();
    
    void closeLogger();

    PlayerColor getColor();
}

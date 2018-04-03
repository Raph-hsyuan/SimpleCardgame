package cpa.inventeur;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Jiaqi
 * @date 2018-4-02
 */
public class Table {
    
    private static Table instance;
    private static final int SIZEOFTABLE = 1;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;
    List<Invention> inventions;
    

    private Table() {
        inventions = new ArrayList<>();
    }
    
    public static Table getInstance() {
        if(instance==null) 
            instance = new Table();
        return instance;
    }
    
    boolean putInvention(Invention invention) {
        if(inventions.size()<SIZEOFTABLE) {
            inventions.add(invention);
            return SUCCESS;
        }
        return FAILED;
    }
    
    Invention getInvention() {
        return inventions.get(0);
    }
    
    void removeAll() {
        inventions.clear();
    }
    
}

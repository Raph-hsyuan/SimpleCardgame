package cpa.inventeur;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static cpa.inventeur.Level.*;

public class Main {
    
    static Random s = new Random();

    public static void main(String[] args) {
        Map<String, Level> builder = new HashMap<>();
        builder.put("huang", SIMPLE);
        builder.put("wu", SIMPLE);
        builder.put("liu", SIMPLE);
        builder.put("yu", SIMPLE);
        builder.put("super", NORMAL);
        GameEngine mygame = new GameEngine(builder);
        mygame.gameStart();
    }

}

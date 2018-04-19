package cpa.inventeur;

import java.util.HashMap;
import java.util.Map;
import static cpa.inventeur.Level.*;

public class Main {

    public static void main(String[] args) {
        Map<String,Level> builder = new HashMap<>();

        builder.put("huang", SIMPLE);
        builder.put("wu", NORMAL);
        builder.put("liu", NORMAL);
        builder.put("yu", NORMAL);
        builder.put("zhang", SIMPLE);

        
        GameEngine mygame = new GameEngine(builder);
        mygame.gameStart();
    }

}

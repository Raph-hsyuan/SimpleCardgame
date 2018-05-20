package cpa.inventeur.launcher;

import static cpa.inventeur.cardsenums.Level.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cpa.inventeur.cardsenums.Level;
import cpa.inventeur.controller.GameEngine;

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

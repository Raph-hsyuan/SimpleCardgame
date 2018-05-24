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
        builder.put("RobotSimple1", SIMPLE);
        builder.put("RobotSimple2", SIMPLE);
        builder.put("RobotSimple3", SIMPLE);
        builder.put("RobotSimple4", SIMPLE);
        builder.put("RobotNormal0", NORMAL);
        GameEngine mygame = new GameEngine(builder);
        mygame.gameStart();
    }

}

package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static cpa.inventeur.Inventor.*;
import static cpa.inventeur.Invention.*;
import static cpa.inventeur.PlayerColor.*;

class PlayerConsoleTest {
    Table table = Table.getInstance();
    RobotSimple robot;
    List<Inventor> inventors = new ArrayList<>();
    PlayerConsole console;    
    
    @BeforeEach
    void initial() {
        table.removeAll();
        table.putInvention(CAR);
        table.putInvention(PLANE);
        table.putInvention(BOAT);
        table.putInvention(BIKE);
        table.initialInventions();
        console = new PlayerConsole(RED);
        console.initialInventors();
    }
    
    @Test
    void testSend() {
        console.send(EDISON, CAR);
        assertTrue(!CAR.isFinished());
        assertTrue(!EDISON.isFree());        
        boolean result = console.send(EDISON, CAR);
        assertEquals(false,result);      
    }

    @Test
    void testSetAllFree() {
        EINSTEIN.setBusy();
        EDISON.setBusy();
        console.setAllFree();
        assertEquals(true,EINSTEIN.isFree()&&EDISON.isFree());
    }

    @Test
    void testGetInventors() {
    	inventors.add(CURIE);
        inventors.add(EDISON);
        inventors.add(EINSTEIN);
        inventors.add(TESLA);
        assertEquals(inventors,console.getInventors());
    }

    @Test
    void testGetLibres() {
        console.setAllFree();
        CURIE.setBusy();
        EINSTEIN.setBusy();
        EDISON.setBusy();
        TESLA.setBusy();
        assertTrue(console.getLibres().isEmpty());
        console.setAllFree();
        EINSTEIN.setBusy();
        assertEquals(CURIE,console.getLibres().get(0));
    }

    @Test
    void testAddPoint() {
        console.addPoint();
        assertEquals(1,console.getScore());
    }

}

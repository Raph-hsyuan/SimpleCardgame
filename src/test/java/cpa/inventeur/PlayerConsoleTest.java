package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static cpa.inventeur.Inventor.*;
import static cpa.inventeur.Invention.*;
import static cpa.inventeur.PlayerColor.*;
import static cpa.inventeur.Ticket.*;

class PlayerConsoleTest {
    Table table = Table.getInstance();
    RobotSimple robot;
    List<Inventor> inventors = new ArrayList<>();
    PlayerConsole consoleR;    
    PlayerConsole consoleY;  
    PlayerConsole consoleP;  
    PlayerConsole consoleB;  
    PlayerConsole consoleG;  
    
    @BeforeEach
    void initial() {
        table.removeAll();
        table.putInvention(CAR);
        table.putInvention(PLANE);
        table.putInvention(BOAT);
        table.putInvention(BIKE);
        table.initialInventions();
        consoleR = new PlayerConsole(RED);
        consoleY = new PlayerConsole(YELLOW);
        consoleP = new PlayerConsole(PURPLE);
        consoleB = new PlayerConsole(BLUE);
        consoleG = new PlayerConsole(GREEN);
        consoleR.initialInventors();
    }
   
    @Test
    void testSend() {
        CAR.initial();
        consoleR.send(EDISON, CAR);
        assertTrue(!CAR.isFinished());
        assertTrue(!EDISON.isFree());        
        boolean result1 = consoleR.send(EDISON, CAR);
        consoleR.setNewTurn();   
        EDISON.setFree();
        boolean result2 = consoleR.send(EDISON, CAR);
        EDISON.setFree();
        boolean result10 = consoleR.send(EDISON, CAR);
        consoleR.setNewTurn();
        boolean result3 = consoleR.send(EDISON, CAR);
        consoleR.setNewTurn();
        boolean result4 = consoleR.send(WATT, CAR);
        boolean result5 = consoleP.send(WATT, CAR);
        consoleP.setNewTurn();
        boolean result7 = consoleP.send(LOVELACE, CAR);
        consoleP.setNewTurn();
        boolean result6 = consoleP.send(LAVOISIER, CAR);
        consoleR.setNewTurn();
        LAVOISIER.setFree();
        boolean result8 = consoleP.send(LAVOISIER, CAR);
        consoleP.setNewTurn();
        LAVOISIER.setBusy();
        boolean result9 = consoleP.send(LAVOISIER, TOWER);
        consoleP.setNewTurn(); 
        
        assertEquals(false,result1);
        assertEquals(true,result2); 
        assertEquals(true,result3);
        assertEquals(false,result4);
        assertEquals(true,result5); 
        assertEquals(true,result6); 
        assertEquals(false,result7);
        assertEquals(false,result8);
        assertEquals(false,result9); 
        assertEquals(false,result10); 
    }

    @Test
    void testSetAllFree() {
        EINSTEIN.setBusy();
        EDISON.setBusy();
        consoleR.setAllFree();
        assertEquals(true,EINSTEIN.isFree()&&EDISON.isFree());
        assertEquals(false,consoleR.setAllFree());
        consoleR.setNewTurn();
    }

    @Test
    void testGetInventors() {
    	inventors.add(CURIE);
        inventors.add(EDISON);
        inventors.add(EINSTEIN);
        inventors.add(TESLA);
        assertEquals(inventors,consoleR.getInventors());
    }

    @Test
    void testGetLibres() {
        consoleR.setNewTurn();
        consoleR.setAllFree();
        CURIE.setBusy();
        EINSTEIN.setBusy();
        EDISON.setBusy();
        TESLA.setBusy();
        assertTrue(consoleR.getLibres().isEmpty());
        consoleR.setNewTurn();
        consoleR.setAllFree();
        EINSTEIN.setBusy();
        assertEquals(CURIE,consoleR.getLibres().get(0));
    }

//    @Test
//    void testAddPoint() {
//        consoleR.addPoint();
//        assertEquals(1,consoleR.getScore());
//    }
    
    @Test 
    void testPickTicket(){
        CAR.addTicket(ADDONEPOINT);
        CAR.addTicket(SETALLFREE);
        consoleR.pickTicket(CAR,ADDONEPOINT);
        assertTrue(consoleR.useTicket(ADDONEPOINT));
        assertFalse(consoleR.useTicket(ADDONEPOINT));
        consoleR.pickTicket(CAR,SETALLFREE);
        assertTrue(consoleR.useTicket(SETALLFREE));
    }
    
    @Test
    void testHasTicket() {
        CAR.addTicket(ADDONEPOINT);
        CAR.addTicket(SETALLFREE);
        CAR.addTicket(ADDONEPOINT);
        consoleR.pickTicket(CAR,ADDONEPOINT);
        consoleR.pickTicket(CAR,ADDONEPOINT);
        consoleR.pickTicket(CAR,SETALLFREE);
        assertTrue(consoleR.getTicket().contains(ADDONEPOINT));
        assertTrue(consoleR.getTicket().contains(SETALLFREE));
        assertTrue(consoleR.useTicket(ADDONEPOINT));
        assertTrue(consoleR.getTicket().contains(ADDONEPOINT));
        assertTrue(consoleR.useTicket(ADDONEPOINT));
        assertFalse(consoleR.getTicket().contains(ADDONEPOINT));  
        assertTrue(consoleR.getTicket().contains(SETALLFREE)); 
        
    }

}

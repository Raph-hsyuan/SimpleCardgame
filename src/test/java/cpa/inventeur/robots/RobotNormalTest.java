package cpa.inventeur.robots;

import static cpa.inventeur.cardsenums.Invention.*;
import static cpa.inventeur.cardsenums.PlayerColor.*;
import static cpa.inventeur.cardsenums.Ticket.*;
import static cpa.inventeur.cardsenums.Inventor.*;
import static java.util.logging.Level.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cpa.inventeur.cardsenums.Invention;
import cpa.inventeur.cardsenums.Inventor;
import cpa.inventeur.controller.PlayerConsole;
import cpa.inventeur.controller.Table;
import cpa.inventeur.robots.Robot;
import cpa.inventeur.robots.RobotNormal;

class RobotNormalTest {
    Table table = Table.getInstance();
    Robot robot;
    List<Inventor> inventors = new ArrayList<>();
    private static final Logger LOG = Logger.getLogger("RobotInfo");
    
    @BeforeEach
    void initial() {
        LOG.setLevel(OFF);
        table.removeAll();
        table.putInvention(CAR);
        table.putInvention(PLANE);
        table.putInvention(BOAT);
        table.putInvention(BIKE);
        table.initialInventions();
    }
    
    
    /**
     * CAR: 3,1,2,2
     * CURIE 1,1,0,0
     * PASCAL 0,0,1,1
     * EINSTEIN 1,0,0,1
     * NEWTON 1,0,1,0
     * ORESME 1,0,0,1 
     */
    @Test
    void testToFinish() {
        PlayerConsole console = new PlayerConsole(RED);
        console.initialInventors();
        RobotNormal robot = new RobotNormal("TESTER1",console);
        this.set2112(CAR);
        assertTrue(robot.toFinish(NEWTON,CAR));
        assertFalse(robot.toFinish(PASCAL,CAR));
        assertFalse(robot.toFinish(CURIE,CAR));
        assertFalse(robot.toFinish(EINSTEIN,CAR));
        CAR.addInventor(ORESME);
        assertTrue(robot.toFinish(PASCAL,CAR));
    }
    
    @Test 
    void testHasLibreToUse() {
        PlayerConsole console = new PlayerConsole(RED);
        console.initialInventors();
        RobotNormal robot = new RobotNormal("TESTER1",console);
        CURIE.setBusy();
        EDISON.setBusy(); 
        assertTrue(robot.hasLibreToUse());
        EINSTEIN.setBusy();
        assertTrue(robot.hasLibreToUse());
        TESLA.setBusy();
        assertEquals(0,console.getLibres().size());
        assertFalse(robot.hasLibreToUse());
        assertEquals(4,console.getLibres().size());
        for(Inventor inv : console.getLibres())
            inv.setBusy();
        CAR.addTicket(SETALLFREE);
        console.pickTicket(CAR,SETALLFREE);
        assertTrue(robot.hasLibreToUse());
        
    }
    
    @Test
    void testFinishOneInv() {
        PlayerConsole console = new PlayerConsole(RED);
        console.initialInventors();
        RobotNormal robot = new RobotNormal("TESTER1",console);
        this.set2112(CAR);
        assertFalse(robot.finishOneInv());
        CAR.addInventor(ORESME);
        for(Inventor inv : console.getLibres())
            inv.setBusy();
        assertFalse(robot.finishOneInv());
        console.setAllFree();
        assertFalse(robot.finishOneInv());
        console.setNewTurn();
        assertTrue(robot.finishOneInv());
        assertTrue(CAR.isFinished());
    }

    void set2112(Invention inv) {
        inv.addInventor(CURIE);
        inv.addInventor(PASCAL);
        inv.addInventor(EINSTEIN);
    }
    
    /**
     * CURIE(1, 1, 0, 0), EDISON(1, 0, 0, 1), EINSTEIN(1, 0, 0, 1), TESLA(0, 0, 2, 0)
     */
    @Test
    void testFindMaxMatch() {
        PlayerConsole console = new PlayerConsole(RED);
        console.initialInventors();
        RobotNormal robot = new RobotNormal("TESTER1",console);
        assertEquals(2,robot.findMaxMatch());
        this.set2112(CAR);//1,0,1,0
        this.set2112(PLANE);//0,3,0,1
        this.set2112(BOAT);//1,2,2,0
        this.set2112(BIKE);//0,0,1,0
        assertEquals(2,robot.findMaxMatch());
        assertEquals(BOAT,robot.bestInvention);
        assertEquals(CURIE,robot.bestInventor);
        BOAT.addInventor(CURIE);//0,1,2,0
        BOAT.addInventor(TESLA);//0,1,0,0
        assertEquals(1,robot.findMaxMatch());
        assertEquals(CAR,robot.bestInvention);
        assertEquals(CURIE,robot.bestInventor);
    }
    
   @Test
   void testToPlay() {
       PlayerConsole console = new PlayerConsole(RED);
       console.initialInventors();
       robot = new RobotNormal("TESTER1", console);
       robot.closeLogger();
       console.setNewTurn();
       console.setAllFree();
       console.setNewTurn();
       robot.toPlay();
       table.removeFinished();
       assertEquals(4, table.getInventions().size());
       assertEquals(3, console.getLibres().size());
       console.setNewTurn();
       robot.toPlay();
       table.removeFinished();
       assertEquals(4, table.getInventions().size());
       assertEquals(2, console.getLibres().size());
       console.setNewTurn();
       robot.toPlay();
       table.removeFinished();
       assertEquals(4, table.getInventions().size());
       assertEquals(1, console.getLibres().size());
       console.setNewTurn();
       robot.toPlay();
       table.removeFinished();
       assertEquals(3, table.getInventions().size());
       assertEquals(0, console.getLibres().size());
       console.setNewTurn();
       robot.toPlay();
       table.removeFinished();
       assertFalse(console.getLibres().isEmpty()); 
       assertEquals(3, table.getInventions().size());
       assertEquals(4, console.getLibres().size());
   }
    
    @Test
    void testGetColor() {
        PlayerConsole console = new PlayerConsole(RED);
        console.initialInventors();
        robot = new RobotNormal("TESTER1",console);
        assertEquals(RED,robot.getColor());
    }
    
    @Test
    void testGetConsole() {
        PlayerConsole console = new PlayerConsole(GREEN);
        console.initialInventors();
        robot = new RobotNormal("TESTER1",console);
        assertEquals(console,robot.getConsole());
    }
    
    @Test
    void testChooseTicket() {
        PlayerConsole console = new PlayerConsole(GREEN);
        console.initialInventors();
        robot = new RobotNormal("TESTER1",console);
        CAR.addTicket(ADDONEPOINT);
        CAR.addTicket(SETALLFREE);
        robot.chooseTicket(CAR);
        assertEquals(1,CAR.getTicket().size());
        robot.chooseTicket(CAR);
        assertEquals(0,CAR.getTicket().size());
    }

    @Test
    void testUseTicket() {
        PlayerConsole console = new PlayerConsole(GREEN);
        console.initialInventors();
        robot = new RobotNormal("TESTER1",console);
        CAR.addTicket(SETALLFREE);
        robot.chooseTicket(CAR);
        assertTrue(robot.useTicket(SETALLFREE));
    }

}

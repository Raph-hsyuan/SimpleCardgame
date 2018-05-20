package cpa.inventeur.robots;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cpa.inventeur.cardsenums.Inventor;
import cpa.inventeur.controller.PlayerConsole;
import cpa.inventeur.controller.Table;
import cpa.inventeur.robots.RobotSimple;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static cpa.inventeur.cardsenums.Invention.*;
import static cpa.inventeur.cardsenums.PlayerColor.*;
import static cpa.inventeur.cardsenums.Ticket.*;
import static java.util.logging.Level.*;

class RobotSimpleTest {
    Table table = Table.getInstance();
    RobotSimple robot;
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

    @Test
    void testToPlay() {
        PlayerConsole console = new PlayerConsole(RED);
        console.initialInventors();
        robot = new RobotSimple("TESTER1",console);
        robot.closeLogger();
        console.setAllFree();
        console.setNewTurn();
        robot.toPlay();
        table.removeFinished();
        assertEquals(4,table.getInventions().size());
        assertEquals(3,console.getLibres().size());
        console.setNewTurn();
        robot.toPlay();
        table.removeFinished();
        assertEquals(4,table.getInventions().size());
        assertEquals(2,console.getLibres().size());
        console.setNewTurn();
        robot.toPlay();
        table.removeFinished();
        assertEquals(4,table.getInventions().size());
        assertEquals(1,console.getLibres().size());
        console.setNewTurn();
        robot.toPlay();
        table.removeFinished();
        assertEquals(4,table.getInventions().size());
        assertEquals(0,console.getLibres().size());
        console.setNewTurn();
        robot.toPlay();
        table.removeFinished();
        assertFalse(console.getLibres().isEmpty());
        assertEquals(4,table.getInventions().size());
        assertEquals(4,console.getLibres().size());
    }
    
    @Test
    void testGetColor() {
        PlayerConsole console = new PlayerConsole(RED);
        console.initialInventors();
        robot = new RobotSimple("TESTER1",console);
        assertEquals(RED,robot.getColor());
    }
    
    @Test
    void testGetConsole() {
        PlayerConsole console = new PlayerConsole(GREEN);
        console.initialInventors();
        robot = new RobotSimple("TESTER1",console);
        assertEquals(console,robot.getConsole());
    }
    
    @Test
    void testChooseTicket() {
        PlayerConsole console = new PlayerConsole(GREEN);
        console.initialInventors();
        robot = new RobotSimple("TESTER1",console);
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
        robot = new RobotSimple("TESTER1",console);
        CAR.addTicket(SETALLFREE);
        robot.chooseTicket(CAR);
        assertTrue(robot.useTicket(SETALLFREE));
    }
}

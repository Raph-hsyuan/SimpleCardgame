package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;
import static cpa.inventeur.Level.*;
import static cpa.inventeur.Invention.CAR;
import static cpa.inventeur.PlayerColor.*;
import static cpa.inventeur.Inventor.*;
import static java.util.logging.Level.OFF;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class GameEngineTest {

    Map<String, Level> build;
    Table table = Table.getInstance();
    GameEngine gameEngine;
    private static final Logger LOG = Logger.getLogger("RobotInfo");
    PlayerConsole red;
    PlayerConsole blue;
    PlayerConsole yellow;
    
    @BeforeEach
    void initial() {
        LOG.setLevel(OFF);
        build = new HashMap<>();
        build.put("Tester1", SIMPLE);
        build.put("Tester2", SIMPLE);
        build.put("Tester3", NORMAL);
        gameEngine = new GameEngine(build);
        table.putInvention(CAR);
        gameEngine.initialGame();
        CAR.initial();
        yellow = gameEngine.getRobot(YELLOW).getConsole();
        red = gameEngine.getRobot(RED).getConsole();
        blue = gameEngine.getRobot(BLUE).getConsole();
    }

    @Test
    void test1ChooseTicket() {
        CAR.addTicket(Ticket.SETALLFREE);
        CAR.addTicket(Ticket.SETALLFREE);
        CAR.addTicket(Ticket.SETALLFREE);
        CAR.addTicket(Ticket.SETALLFREE);
        yellow.send(PASCAL, CAR);
        blue.send(ORESME, CAR);
        red.send(EDISON, CAR);
        int before = CAR.getTicket().size();
        gameEngine.chooseTicket(CAR);
        int after = CAR.getTicket().size();
        int expect;
        expect = before - 3;
        assertEquals(expect ,after);
    }
    
    @Test
    void test2ChooseTicket() {
        CAR.addTicket(Ticket.SETALLFREE);
        CAR.addTicket(Ticket.SETALLFREE);
        yellow.send(PASCAL, CAR);
        blue.send(ORESME, CAR);
        red.send(EDISON, CAR);
        gameEngine.chooseTicket(CAR);
        int after = CAR.getTicket().size();
        assertEquals(0 ,after);
        assertEquals(0,red.getTicket().size());
    }
    
    @Test
    void testSetNewTurn() {
        assertTrue(yellow.send(PASCAL, CAR));
        assertFalse(yellow.send(BOWLE, CAR));
        gameEngine.setNewTurn();
        assertTrue(yellow.send(BOWLE, CAR));
    }
    
    @Test
    void testGetRobot() {
        assertTrue(gameEngine.getRobot(RED) instanceof RobotNormal);
        Executable exceptionTest = () -> gameEngine.getRobot(null);
        assertThrows(IllegalStateException.class,exceptionTest,"this Color not exist");
    }
    
    @Test
    void testAddTickets() {
        gameEngine.addTickets();
        gameEngine.addTickets();
        gameEngine.addTickets();
        gameEngine.addTickets();
        assertTrue(CAR.getTicket().size()>0);        
    }
    
    @Test
    void testPutInventions() {
        gameEngine.putInventions();
        assertEquals(6+1,table.getInventions().size());
        build.put("Tester4", NORMAL);
        build.put("Tester5", NORMAL);
        gameEngine = new GameEngine(build);
        assertEquals(7,table.getInventions().size());
    }


    @Test
    void testGetWinner() {
        assertEquals(3,gameEngine.getWinner().size());
        build.put("Tester4", NORMAL);
        build.put("Tester5", NORMAL);
        gameEngine = new GameEngine(build);
        assertEquals(5,gameEngine.getWinner().size());   
        gameEngine.updateScore(BLUE, 1);
        assertEquals(1,gameEngine.getWinner().size());    
    }
}
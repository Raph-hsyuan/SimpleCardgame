package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static cpa.inventeur.Inventor.*;
class RobotSimpleTest {
    Table table = Table.getInstance();
    RobotSimple robot;
    Invention invention1 = new Invention("invention1");
    Invention invention2 = new Invention("invention2");
    Invention invention3 = new Invention("invention3");
    Invention invention4 = new Invention("invention4");
    Inventor inventorA = NEWTON;
    Inventor inventorB = EDISON;
    List<Inventor> inventors = new ArrayList<>();
    
    @BeforeEach
    void initial() {
        inventorA.initial();
        inventorB.initial();
        table.removeAll();
        table.putInvention(invention1);
        table.putInvention(invention2);
        table.putInvention(invention3);
        table.putInvention(invention4);
        inventors.add(inventorA);
        inventors.add(inventorB);
    }

    @Test
    void testToPlay() {
        PlayerConsole console = new PlayerConsole(inventors);
        robot = new RobotSimple("TESTER1",console);
        console.setAllFree();
        robot.toPlay();
        table.removeFinished();
        assertEquals(3,table.getInventions().size());
        assertEquals(1,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertEquals(2,table.getInventions().size());
        assertEquals(0,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertEquals(2,table.getInventions().size());
        assertEquals(2,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertEquals(1,table.getInventions().size());
        assertEquals(1,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertTrue(console.getLibres().isEmpty());
        assertEquals(0,table.getInventions().size());
        assertEquals(0,console.getLibres().size());
    }
    
    @AfterEach
    void testGetScore() {
        assertEquals(4,robot.getScore());
    }
}

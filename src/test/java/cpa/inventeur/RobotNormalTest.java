package cpa.inventeur;

import static cpa.inventeur.Invention.BIKE;
import static cpa.inventeur.Invention.BOAT;
import static cpa.inventeur.Invention.CAR;
import static cpa.inventeur.Invention.PLANE;
import static cpa.inventeur.Inventor.EDISON;
import static cpa.inventeur.Inventor.NEWTON;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RobotNormalTest {
    Table table = Table.getInstance();
    Robot robot;
    Invention invention1 = CAR;
    Invention invention2 = PLANE;
    Invention invention3 = BOAT;
    Invention invention4 = BIKE;
    Inventor inventorA = NEWTON;
    Inventor inventorB = EDISON;
    List<Inventor> inventors = new ArrayList<>();
    
    @BeforeEach
    void initial() {
        inventorA.initial();
        inventorB.initial();
        table.removeAll();
        invention1.initial();
        invention2.initial();
        invention3.initial();
        invention4.initial();
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
        robot = new RobotNormal("TESTER1",console);
        console.setAllFree();
        robot.toPlay();
        table.removeFinished();
        assertEquals(4,table.getInventions().size());
        assertEquals(1,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertEquals(4,table.getInventions().size());
        assertEquals(0,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertEquals(4,table.getInventions().size());
        assertEquals(2,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertEquals(3,table.getInventions().size());
        assertEquals(1,console.getLibres().size());
        robot.toPlay();
        table.removeFinished();
        assertTrue(console.getLibres().isEmpty());
        assertEquals(3,table.getInventions().size());
        assertEquals(0,console.getLibres().size());
    }
    
    @AfterEach
    void testGetScore() {
        assertEquals(1,robot.getScore());
    }
}

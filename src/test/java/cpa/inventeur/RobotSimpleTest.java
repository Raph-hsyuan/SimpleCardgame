package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static cpa.inventeur.Inventor.*;
import static cpa.inventeur.Invention.*;
class RobotSimpleTest {
    Table table = Table.getInstance();
    RobotSimple robot;
    List<Inventor> inventors = new ArrayList<>();

    @BeforeEach
    void initial() {
        NEWTON.initial();
        EDISON.initial();
        table.removeAll();
        CAR.initial();
        PLANE.initial();
        BOAT.initial();
        BIKE.initial();
        table.putInvention(CAR);
        table.putInvention(PLANE);
        table.putInvention(BOAT);
        table.putInvention(BIKE);
        inventors.add(NEWTON);
        inventors.add(EDISON);
    }

    @Test
    void testToPlay() {
        PlayerConsole console = new PlayerConsole(inventors);
        robot = new RobotSimple("TESTER1",console);
        robot.closeLogger();
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

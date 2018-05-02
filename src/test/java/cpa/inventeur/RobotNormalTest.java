package cpa.inventeur;

import static cpa.inventeur.PlayerColor.*;
import static cpa.inventeur.Invention.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RobotNormalTest {
    Table table = Table.getInstance();
    Robot robot;
    List<Inventor> inventors = new ArrayList<>();

    @BeforeEach
    void initial() {
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
        assertTrue(!console.getLibres().isEmpty());
        assertEquals(3, table.getInventions().size());
        assertEquals(4, console.getLibres().size());
    }

    @AfterEach
    void testGetScore() {
        assertEquals(1, robot.getScore());
    }
}

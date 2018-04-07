package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class RobotSimpleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Table table = Table.getInstance();
    RobotSimple robot;
    Invention invention1 = new Invention("invention1");
    Invention invention2 = new Invention("invention2");
    Invention invention3 = new Invention("invention3");
    Invention invention4 = new Invention("invention4");
    Inventor inventorA = new Inventor("inventorA");
    Inventor inventorB = new Inventor("inventorB");
    List<Inventor> inventors = new ArrayList<>();
    
    @BeforeEach
    void initial() {
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
        System.setOut(new PrintStream(outContent));
        robot.toPlay();
        assertEquals("TESTER1 send inventorA-------->invention1\r\n", outContent.toString());
        table.removeFinished();
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        robot.toPlay();
        assertEquals("TESTER1 send inventorB-------->invention2\r\n", outContent.toString());
        table.removeFinished();
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        robot.toPlay();
        assertEquals("TESTER1 set All Free\r\n", outContent.toString());
        table.removeFinished();
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        robot.toPlay();
        assertEquals("TESTER1 send inventorA-------->invention3\r\n", outContent.toString());
        table.removeFinished();
        outContent.reset();
        System.setOut(new PrintStream(outContent));
        robot.toPlay();
        assertEquals("TESTER1 send inventorB-------->invention4\r\n", outContent.toString());
        assertTrue(console.getLibres().isEmpty());
    }
    
    @AfterEach
    void testGetScore() {
        assertEquals(4,robot.getScore());
    }
}

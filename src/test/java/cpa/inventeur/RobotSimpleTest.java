package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class RobotSimpleTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Table table = Table.getInstance();
    Invention invention1 = new Invention("invention1");
    Inventor inventor = new Inventor("inventor1");
    
    @Test
    void testToPlay() {
        table.putInvention(invention1);
        RobotSimple robot = new RobotSimple(table,inventor);
        robot.toPlay();
        assertTrue(table.getInvention().isFinished());
        System.setOut(new PrintStream(outContent));
        robot.toPlay();
        assertEquals("NOTHING TO DO\n", outContent.toString());
    }

}

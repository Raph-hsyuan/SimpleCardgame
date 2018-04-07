package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerConsoleTest {
    Table table = Table.getInstance();
    RobotSimple robot;
    Invention invention1 = new Invention("invention1");
    Invention invention2 = new Invention("invention2");
    Invention invention3 = new Invention("invention3");
    Invention invention4 = new Invention("invention4");
    Inventor inventorA = new Inventor("inventorA");
    Inventor inventorB = new Inventor("inventorB");
    List<Inventor> inventors = new ArrayList<>();
    PlayerConsole console;    
    
    @BeforeEach
    void initial() {
        table.removeAll();
        table.putInvention(invention1);
        table.putInvention(invention2);
        table.putInvention(invention3);
        table.putInvention(invention4);
        inventors.add(inventorA);
        inventors.add(inventorB);
        console = new PlayerConsole(inventors);
    }
    
    @Test
    void testSend() throws RuntimeException {
        console.send(inventorA, invention1);
        assertTrue(invention1.isFinished());
        assertTrue(!inventorA.isFree());        
        Throwable t = null;
        try{
            console.send(inventorA, invention1);;
        }catch(Exception ex){
            t = ex;
        }
        assertNotNull(t);
        assertTrue(t instanceof RuntimeException);
        assertEquals("Send ERROR!truetruefalsefalse",t.getMessage());
        
    }

    @Test
    void testSetAllFree() {
        inventorA.setBusy();
        inventorB.setBusy();
        console.setAllFree();
        assertEquals(true,inventorA.isFree()&&inventorB.isFree());
    }

    @Test
    void testGetInventors() {
        assertEquals(inventors,console.getInventors());
    }

    @Test
    void testGetLibres() {
        console.setAllFree();
        inventorA.setBusy();
        inventorB.setBusy();
        assertTrue(console.getLibres().isEmpty());
        console.setAllFree();
        inventorA.setBusy();
        assertEquals(inventorB,console.getLibres().get(0));
    }

    @Test
    void testAddPoint() {
        console.addPoint();
        assertEquals(1,console.getScore());
    }

}

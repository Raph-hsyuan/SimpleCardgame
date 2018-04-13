package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static cpa.inventeur.Inventor.*;
import static cpa.inventeur.Invention.*;

class PlayerConsoleTest {
    Table table = Table.getInstance();
    RobotSimple robot;
    List<Inventor> inventors = new ArrayList<>();
    PlayerConsole console;    
    
    @BeforeEach
    void initial() {
        NEWTON.initial();
        EDISON.initial();
        CAR.initial();
        PLANE.initial();
        BOAT.initial();
        BIKE.initial();
        table.removeAll();
        table.putInvention(CAR);
        table.putInvention(PLANE);
        table.putInvention(BOAT);
        table.putInvention(BIKE);
        inventors.add(NEWTON);
        inventors.add(EDISON);
        console = new PlayerConsole(inventors);
    }
    
    @Test
    void testSend() throws RuntimeException {
        console.send(NEWTON, CAR);
        assertTrue(!CAR.isFinished());
        assertTrue(!NEWTON.isFree());        
        Throwable t = null;
        try{
            console.send(NEWTON, CAR);;
        }catch(Exception ex){
            t = ex;
        }
        assertNotNull(t);
        assertTrue(t instanceof RuntimeException);
        assertEquals("Send ERROR!truetruetruefalse",t.getMessage());
        
    }

    @Test
    void testSetAllFree() {
        NEWTON.setBusy();
        EDISON.setBusy();
        console.setAllFree();
        assertEquals(true,NEWTON.isFree()&&EDISON.isFree());
    }

    @Test
    void testGetInventors() {
        assertEquals(inventors,console.getInventors());
    }

    @Test
    void testGetLibres() {
        console.setAllFree();
        NEWTON.setBusy();
        EDISON.setBusy();
        assertTrue(console.getLibres().isEmpty());
        console.setAllFree();
        NEWTON.setBusy();
        assertEquals(EDISON,console.getLibres().get(0));
    }

    @Test
    void testAddPoint() {
        console.addPoint();
        assertEquals(1,console.getScore());
    }

}

package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static cpa.inventeur.Invention.*;

class TableTest {

    Table table = Table.getInstance();
    
    @BeforeEach
    void clear() {
        table.removeAll();
        CAR.initial();
        PLANE.initial();
        BOAT.initial();
        BIKE.initial();
    }
    
    @Test
    void testGetInvention() {
        Invention invention1 = CAR;
        table.putInvention(invention1);
        assertEquals(invention1,table.getInventions().get(0));
        table.removeAll();
    }
    
    @Test
    void testPutInvention() {
        Invention invention1 = CAR;
        Invention invention2 = PLANE;
        Invention invention3 = BOAT;
        Invention invention4 = BIKE;
        assertTrue(table.putInvention(invention1));
        assertTrue(table.putInvention(invention2));
        assertTrue(table.putInvention(invention3));
        assertTrue(table.putInvention(invention4));
        assertTrue(table.putInvention(invention4));
        assertTrue(table.putInvention(invention4));
        assertTrue(table.putInvention(invention4));
        assertTrue(table.putInvention(invention4));
        assertFalse(table.putInvention(invention4));
        assertEquals(8,table.getInventions().size());
        assertEquals(invention1,table.getInventions().get(0));
        assertEquals(invention2,table.getInventions().get(1));
        assertEquals(invention3,table.getInventions().get(2));
        assertEquals(invention4,table.getInventions().get(3));
        table.removeAll();
    }

    @Test 
    void testRemoveAll(){
        Invention invention1 = CAR;
        Invention invention2 = PLANE;
        Invention invention3 = BOAT;
        table.putInvention(invention1);
        table.putInvention(invention2);
        table.putInvention(invention3);
        table.removeAll();
        assertTrue(table.getInventions().isEmpty());
    }
    
    @Test
    void testRemoveFinished() {
        Invention invention1 = CAR;
        Invention invention2 = PLANE;
        Invention invention3 = BOAT;
        table.putInvention(invention1);
        table.putInvention(invention2);
        table.putInvention(invention3);
        assertEquals(3,table.getInventions().size());
        invention1.setFinished();
        invention2.setFinished();
        table.removeFinished();
        assertEquals(1,table.getInventions().size());
        table.removeAll();
    }

    @Test
    void testGetNotFinished() {
        Invention invention1 = CAR;
        Invention invention2 = PLANE;
        Invention invention3 = BOAT;
        table.putInvention(invention1);
        table.putInvention(invention2);
        table.putInvention(invention3);
        CAR.setFinished();
        BOAT.setFinished();
        List<Invention> nf = table.getNotFinished();
        assertEquals(nf.get(0),PLANE);
    }
    
    @Test
    void testInitial() {
        Invention invention1 = CAR;
        Invention invention2 = PLANE;
        Invention invention3 = BOAT;
        table.putInvention(invention1);
        table.putInvention(invention2);
        table.putInvention(invention3);
        CAR.setFinished();
        BOAT.setFinished();
        PLANE.addInventor(Inventor.NEWTON);
        table.initialInventions();
        assertEquals(3,table.getNotFinished().size());
        assertEquals(2,PLANE.getDemandeValue(Skill.PHYSICS));
        
    }
    
    
}

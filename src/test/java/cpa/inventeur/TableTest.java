package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableTest {

    Table table = Table.getInstance();
    
    @BeforeEach
    void clear() {
        table.removeAll();
    }
    
    @Test
    void testGetInvention() {
        Invention invention1 = new Invention("invention1");
        table.putInvention(invention1);
        assertEquals(invention1,table.getInventions().get(0));
        table.removeAll();
    }
    
    @Test
    void testPutInvention() {
        Invention invention1 = new Invention("invention1");
        Invention invention2 = new Invention("invention2");
        Invention invention3 = new Invention("invention3");
        Invention invention4 = new Invention("invention4");
        assertTrue(table.putInvention(invention1));
        assertTrue(table.putInvention(invention2));
        assertTrue(table.putInvention(invention3));
        assertTrue(table.putInvention(invention4));
        assertTrue(!table.putInvention(invention4));
        assertEquals(4,table.getInventions().size());
        assertEquals(invention1,table.getInventions().get(0));
        assertEquals(invention2,table.getInventions().get(1));
        assertEquals(invention3,table.getInventions().get(2));
        assertEquals(invention4,table.getInventions().get(3));
        table.removeAll();
    }

    @Test 
    void testRemoveAll(){
        Invention invention1 = new Invention("invention1");
        Invention invention2 = new Invention("invention2");
        Invention invention3 = new Invention("invention3");
        table.putInvention(invention1);
        table.putInvention(invention2);
        table.putInvention(invention3);
        table.removeAll();
        assertTrue(table.getInventions().isEmpty());
    }
    
    @Test
    void testRemoveFinished() {
        Invention invention1 = new Invention("invention1");
        Invention invention2 = new Invention("invention2");
        Invention invention3 = new Invention("invention3");
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
    
    
}

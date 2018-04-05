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
        assertEquals(invention1,table.getInvention());
        table.removeAll();
    }
    
    @Test
    void testPutInvention() {
        Invention invention1 = new Invention("invention1");
        Invention invention2 = new Invention("invention2");
        assertTrue(table.putInvention(invention1));
        assertTrue(!table.putInvention(invention2));
        assertEquals(1,table.inventions.size());
        assertEquals(invention1,table.inventions.get(0));
        table.removeAll();
    }

}

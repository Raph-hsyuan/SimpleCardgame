package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InventionTest {

    Inventor inventor = new Inventor("tester");
    private static final boolean FINISHED = true;
    private static final boolean INPROGRESS = false;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;

    
    @Test
    void testAddInventor() {
        Invention invention = new Invention("tester");
        boolean resultS = invention.addInventor(inventor);
        boolean resultF = invention.addInventor(inventor);
        int expectSize = 1;
        assertEquals(SUCCESS,resultS);
        assertEquals(FAILED,resultF);
        assertEquals(expectSize,invention.inventors.size());
        
    }

    @Test
    void testGetState() {
        Invention invention = new Invention("tester");
        boolean with0inventor = invention.getState();
        invention.addInventor(inventor);
        boolean with1inventor = invention.getState();
        assertEquals(INPROGRESS,with0inventor);
        assertEquals(FINISHED,with1inventor);
    }

}

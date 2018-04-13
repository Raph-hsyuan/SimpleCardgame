package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import static cpa.inventeur.Inventor.*;
import org.junit.jupiter.api.Test;

class InventionTest {

    Inventor inventor = NEWTON;
    private static final boolean FINISHED = true;
    private static final boolean INPROGRESS = false;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;

    @BeforeEach
    void initial() {
        inventor.initial();
    }
    
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
    void testIsFinished() {
        Invention invention = new Invention("tester");
        boolean with0inventor = invention.isFinished();
        invention.addInventor(inventor);
        boolean with1inventor = invention.isFinished();
        assertEquals(INPROGRESS,with0inventor);
        assertEquals(FINISHED,with1inventor);
    }

}

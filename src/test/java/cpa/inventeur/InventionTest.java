package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import static cpa.inventeur.Inventor.*;
import static cpa.inventeur.Invention.*;
import org.junit.jupiter.api.Test;

class InventionTest {

    private static final boolean FINISHED = true;
    private static final boolean INPROGRESS = false;
    private static final boolean SUCCESS = true;
    private static final boolean FAILED = false;

    @BeforeEach
    void initial() {
        NEWTON.initial();
        CAR.initial();
        PLANE.initial();
        BOAT.initial();
        BIKE.initial();
    }
    
    /**
     * CAR(" CAR  ", 3, 1, 2, 2)
     * NEWTON("newton", 2, 0, 1, 1), 
     * EDISON("edison", 2, 2, 2, 0)
     */
    @Test
    void testAddInventor() {
        boolean resultS = CAR.addInventor(NEWTON);
        boolean resultSS = CAR.addInventor(NEWTON);
        boolean resultF = CAR.addInventor(NEWTON);
        int expectSize = 2;
        assertEquals(SUCCESS,resultS);
        assertEquals(SUCCESS,resultSS);
        assertEquals(FAILED,resultF);
        assertEquals(expectSize,CAR.inventors.size());
    }

    /**
     * CAR(" CAR  ", 3, 1, 2, 2)
     * NEWTON("newton", 2, 0, 1, 1), 
     * EDISON("edison", 2, 2, 2, 0)
     */
    @Test
    void testIsFinished() {
        Invention invention = CAR;
        boolean with0inventor = invention.isFinished();
        invention.addInventor(NEWTON);
        boolean with1inventor = invention.isFinished();
        invention.addInventor(NEWTON);
        boolean with2inventor = invention.isFinished();
        invention.addInventor(EDISON);
        boolean with3inventor = invention.isFinished();
        assertEquals(INPROGRESS,with0inventor);
        assertEquals(INPROGRESS,with1inventor);
        assertEquals(INPROGRESS,with2inventor);
        assertEquals(FINISHED,with3inventor);
    }

}

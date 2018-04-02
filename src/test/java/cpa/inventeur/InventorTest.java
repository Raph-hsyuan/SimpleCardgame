package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InventorTest {

    Inventor inventor = new Inventor("tester");

    @Test
    void testGetState() {
        boolean expect = true;
        assertEquals(expect, inventor.getState());
    }

    @Test
    void testSetBusy() {
        inventor.setBusy();
        boolean expect = false;
        assertEquals(expect, inventor.getState());
    }

    @Test
    void testSetFree() {
        inventor.setFree();
        boolean expect = true;
        assertEquals(expect, inventor.getState());
    }

}

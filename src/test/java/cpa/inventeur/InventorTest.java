package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InventorTest {

    Inventor inventor = new Inventor("tester");

    @Test
    void testGetStatut() {
        boolean expect = true;
        assertEquals(expect, inventor.getStatut());
    }

    @Test
    void testSetBusy() {
        inventor.setBusy();
        boolean expect = false;
        assertEquals(expect, inventor.getStatut());
    }

    @Test
    void testSetFree() {
        inventor.setFree();
        boolean expect = true;
        assertEquals(expect, inventor.getStatut());
    }

}

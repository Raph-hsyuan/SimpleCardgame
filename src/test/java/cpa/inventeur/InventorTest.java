package cpa.inventeur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static cpa.inventeur.Inventor.*;
import static cpa.inventeur.Skill.*;

class InventorTest {

    /**
     * NEWTON name: newton physics: 2 chemistry: 0 machinery: 1 maths: 1
     */
    Inventor inventor = NEWTON;

    @BeforeEach
    void initial() {
        inventor.initial();
    }

    @Test
    void testGetState() {
        boolean expect = true;
        assertEquals(expect, inventor.isFree());
    }

    @Test
    void testSetBusy() {
        inventor.setBusy();
        boolean expect = false;
        assertEquals(expect, inventor.isFree());
    }

    @Test
    void testSetFree() {
        inventor.setFree();
        boolean expect = true;
        assertEquals(expect, inventor.isFree());
    }

    @Test
    void testGetSkillValue() {
        int phy = 2;
        int che = 0;
        int mac = 1;
        int mat = 1;
        assertEquals(phy, inventor.getSkillValue(PHYSICS));
        assertEquals(che, inventor.getSkillValue(CHEMISTRY));
        assertEquals(mac, inventor.getSkillValue(MACHINERY));
        assertEquals(mat, inventor.getSkillValue(MATHS));
    }

}

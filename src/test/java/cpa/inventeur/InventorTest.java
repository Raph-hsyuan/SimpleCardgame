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

    @BeforeEach
    void initial() {
        NEWTON.initial();
        NEWTON.initial();
    }

    @Test
    void testGetState() {
        boolean expect = true;
        assertEquals(expect, NEWTON.isFree());
    }

    @Test
    void testSetBusy() {
        NEWTON.setBusy();
        boolean expect = false;
        assertEquals(expect, NEWTON.isFree());
    }

    @Test
    void testSetFree() {
        NEWTON.setFree();
        boolean expect = true;
        assertEquals(expect, NEWTON.isFree());
    }

    @Test
    void testGetSkillValue() {
        int phy = 1;
        int che = 0;
        int mac = 1;
        int mat = 1;
        assertEquals(phy, NEWTON.getSkillValue(PHYSICS));
        assertEquals(che, NEWTON.getSkillValue(CHEMISTRY));
        assertEquals(mac, NEWTON.getSkillValue(MACHINERY));
        assertEquals(mat, NEWTON.getSkillValue(MATHS));
    }

}

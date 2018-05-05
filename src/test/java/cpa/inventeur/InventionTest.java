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
     * CAR(" CAR ", 3, 1, 2, 2) NEWTON("newton", 2, 0, 1, 1), EDISON("edison", 2, 2,
     * 2, 0)
     */
    @Test
    void testAddInventor() {
        boolean resultS = CAR.addInventor(WATT);
        boolean resultSS = CAR.addInventor(EINSTEIN);
        boolean resultSSS = CAR.addInventor(EINSTEIN);
        boolean resultSSSS = CAR.addInventor(EINSTEIN);
        boolean resultSSSSS = CAR.addInventor(BOWLE);
        boolean resultF = CAR.addInventor(HYPPOKRATES);
        int expectSize = 5;
        assertEquals(SUCCESS, resultS);
        assertEquals(SUCCESS, resultSS);
        assertEquals(SUCCESS, resultSSS);
        assertEquals(SUCCESS, resultSSSS);
        assertEquals(SUCCESS, resultSSSSS);
        assertEquals(FAILED, resultF);
        assertEquals(expectSize, CAR.inventors.size());
    }

    /**
     * CAR(" CAR ", 3, 1, 2, 2) NEWTON("newton", 2, 0, 1, 1), EDISON("edison", 2, 2,
     * 2, 0)
     */
    @Test
    void testIsFinished() {
        boolean with0inventor = CAR.isFinished();
        CAR.addInventor(WATT);
        boolean with1inventor = CAR.isFinished();
        CAR.addInventor(EINSTEIN);
        boolean with2inventor = CAR.isFinished();
        CAR.addInventor(EINSTEIN);
        CAR.addInventor(EINSTEIN);
        CAR.addInventor(EINSTEIN);
        CAR.addInventor(HYPPOKRATES);
        boolean with3inventor = CAR.isFinished();
        assertEquals(INPROGRESS, with0inventor);
        assertEquals(INPROGRESS, with1inventor);
        assertEquals(INPROGRESS, with2inventor);
        assertEquals(FINISHED, with3inventor);
    }
    
    @Test
    void testToStars() {
        String starCARnull = CAR.toStars(Skill.PHYSICS);
        String expect = new String("☆☆☆");
        assertEquals(expect, starCARnull);
        CAR.addInventor(NEWTON);
        CAR.addInventor(NEWTON);
        String starCARnew1 = CAR.toStars(Skill.PHYSICS);
        expect = new String("★★☆");
        assertEquals(expect, starCARnew1);
        CAR.addInventor(NEWTON);
        String starCARnew2 = CAR.toStars(Skill.PHYSICS);
        expect = new String("★★★");
        assertEquals(expect, starCARnew2);
    }

    @Test
    void testSetFinished() {
        CAR.setFinished();
        assertEquals(true,CAR.isFinished());
    }
}

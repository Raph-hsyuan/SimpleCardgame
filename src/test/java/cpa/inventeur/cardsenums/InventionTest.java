package cpa.inventeur.cardsenums;

import static cpa.inventeur.cardsenums.Invention.*;
import static cpa.inventeur.cardsenums.Inventor.*;
import static cpa.inventeur.cardsenums.Ticket.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cpa.inventeur.cardsenums.PlayerColor;
import cpa.inventeur.cardsenums.Skill;

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
        boolean resultSSS = CAR.addInventor(EDISON);
        boolean resultSSSS = CAR.addInventor(HYPATIA);
        boolean resultSSSSS = CAR.addInventor(BOWLE);
        boolean resultF = CAR.addInventor(HYPPOKRATES);
        int expectSize = 4;
        assertEquals(SUCCESS, resultS);
        assertEquals(SUCCESS, resultSS);
        assertEquals(SUCCESS, resultSSS);
        assertEquals(SUCCESS, resultSSSS);
        assertEquals(SUCCESS, resultSSSSS);
        assertEquals(FAILED, resultF);
        assertEquals(expectSize, CAR.contributer.size());
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
    
    @Test
    void testGetTicket() {
        CAR.addTicket(ADDONEPOINT);
        CAR.addTicket(SETALLFREE);
        assertEquals(ADDONEPOINT,CAR.getTicket().get(0));
        assertEquals(SETALLFREE,CAR.getTicket().get(1));
    }
    
    @Test
    void testGetContribute() {
        CAR.addInventor(CURIE);
        assertEquals(2,CAR.getContribute(PlayerColor.RED));
        CAR.addInventor(TESLA);
        CAR.addInventor(GUIENBERG);
        CAR.addInventor(ORESME);
        assertEquals(4,CAR.getContribute(PlayerColor.RED));
        assertEquals(2,CAR.getContribute(PlayerColor.BLUE));
    }
}

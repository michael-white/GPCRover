package test.java.com.mbw.gpcrover;

import main.java.com.mbw.gpcrover.OrientationEnum;
import main.java.com.mbw.gpcrover.Rover;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoverTests {

    @Test
    public void testNewRoverWithValidPlateauHeightParameter() {
        Rover rover = new Rover(3,6);
        assertEquals(6, rover.getPlateauHeight());
    }

    @Test
    public void testNewRoverWithValidPlateauLengthParameter() {
        Rover rover = new Rover(3,6);
        assertEquals(3, rover.getPlateauLength());
    }

    @Test
    public void testDirectionFacingWalkingInCircles() {
        Rover rover = new Rover(3,6, OrientationEnum.valueOf("S").ordinal(), 9, 9);
        rover.processInstructions("LLLLLLLLLLLL");
        assertEquals(rover.getDirectionFacing().name(),"S");

    }

    @Test
    public void testProcessRoverInstructionsHasValidLocation() {
        Rover rover = new Rover(3,6, OrientationEnum.valueOf("S").ordinal(), 9, 9);
        rover.processInstructions("LLRRMM");
        assertEquals(rover.printLocation(), "3 4 S");
    }

}

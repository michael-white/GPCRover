package main.java.com.mbw.gpcrover;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rover {

    /**
     *
     *   x and y coordinates for wehere the rover is on the plateau,  with 0,0 being the bottom left of the plateau
     *
     *   for directionFacing,  the direction will be 1 through 4,  where 1 is North, 2 is West, 3 is South, and 4 is East
     *      and if you are changing the direction you are facing with  (L)eft,  then you would add 1,
     *      and if you are changing the direction you are facing with  (R)ight, then you would subtract 1
     *
     *   so facing West (2) and received an L ...  add 1 and now facing South
     *
     *   if facing East (4) amd received an L ...   ( 4 + 1 ) mod 4
     *
     */


    private int xCoordinate;
    private int yCoordinate;
    private int directionFacing;
    private int plateauLength = 0;
    private int plateauHeight = 0;

    public int getPlateauLength() {
        return plateauLength;
    }

    public int getPlateauHeight() {
        return plateauHeight;
    }

    public void setPlateauLength(int plateauLength) {
        this.plateauLength = plateauLength;
    }

    public void setPlateauHeight(int plateauHeight) {
        this.plateauHeight = plateauHeight;
    }

    public Rover(int plateauLength , int plateauHeight) {
        /*
        *
        * Pleateau dimensions must be equal to or greater than 1 for the length anbd height
        * Any other values will result in an invalid plateau ( either negat5ive length or height or 0 length or height, with dimensions set to 0,0
        *
         */
        if( plateauHeight >=1 && plateauLength >= 1) {
            this.plateauLength = plateauLength;
            this.plateauHeight = plateauHeight;
        } else {
            this.plateauHeight = 0;
            this.plateauLength = 0;
        }
    }

    public boolean plateauIsValid() {
        return ( this.plateauHeight > 0 && this.plateauLength > 0);
    }



    public Rover(int x, int y, int orientation, Integer... plateauDimensions) {
            //here plateauDimensions are optional,  but if supplied, only 2 integers will successfully instantiate the object with the plateauDimensions.x and plateauDimensions.y set equal to the 2 options integers
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.directionFacing = orientation;

       if(plateauDimensions != null && Arrays.asList(plateauDimensions).size() ==2) {
           this.plateauLength = Arrays.asList(plateauDimensions).get(0).intValue();
           this.plateauHeight = Arrays.asList(plateauDimensions).get(1).intValue();
       } else if(plateauDimensions != null ) {
           //  plateau dimensions are being set to 0 height and 0 length because there wasnt exactly 2 values supplied for the dimensions
           this.plateauLength = 0;
           this.plateauHeight = 0;
       }
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getDirectionFacing() {
        return directionFacing;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setDirectionFacing(int directionFacing) {
        this.directionFacing = directionFacing;
    }


    private boolean doInstruction(String instr) {
        /*
        *  valid input is only L or R or M  ...  accommodating lower case as well
         */
            if(!instr.matches("[LlRrMm]")) {
                return false;
            }

            boolean instrResult = false;
            switch (instr.toUpperCase()) {

                case "L" :
                            this.directionFacing = ( (this.directionFacing + 1)% 4);
                            instrResult =  true;
                            break;
                case "R" :
                            this.directionFacing = (this.directionFacing >=1) ?    (  (this.directionFacing - 1) % 4) : 3 ;
                            instrResult = true;
                            break;
                case "M" :
                            instrResult = moveRover();
                            break;

            }

            return instrResult;
    }


    public void processInstructions(String instructions) {

        char[] instructionChars = instructions.toCharArray();
        for (char instruction : instructionChars) {

            doInstruction(String.valueOf(instruction));

        }
    }


    private boolean moveRover() {


        /*
         *       N(0)
         *     W(1) E(3)
         *       S(2)
         */

        switch ( directionFacing) {

            case 0 :
                     if(this.yCoordinate + 1 <= this.plateauHeight) {
                         this.yCoordinate += 1;
                         return true;
                     }
                     break;
            case 1 :
                     if(this.xCoordinate -1 >= 0) {
                         this.xCoordinate -= 1;
                         return true;
                     }
                     break;
            case 2 :
                     if(this.yCoordinate -1 >= 0) {
                         this.yCoordinate -= 1;
                         return true;
                     }
                     break;
            case 3 :
                    if(this.xCoordinate +1 <= this.plateauLength ) {
                        this.xCoordinate += 1;
                        return true;
                    }
                     break;
        }

        //if made it this far then invalid movement resulted in rover trying to move past the plateau dimensions.  or into negative coordinates

        return false;
    }


    private String recodeOrientation(int orientation) {

        switch (orientation) {
            case 0  : return "N";
            case 1 : return "W";
            case 2 : return "S";
            case 3 : return "E";

            default :  return "Z";
        }
    }

    public void printLocation() {
        System.out.println(this.xCoordinate + " " + this.yCoordinate + " " + recodeOrientation(this.directionFacing));
    }

    @Override
    public String toString() {
        return "Rover{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", directionFacing=" + directionFacing +
                ", plateauLength=" + plateauLength +
                ", plateauHeight=" + plateauHeight +
                '}';
    }
}

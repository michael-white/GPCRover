package main.java.com.mbw.gpcrover;


import java.util.Arrays;

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
    private OrientationEnum directionFacing;
    private int plateauLength = 0;
    private int plateauHeight = 0;

    public int getPlateauLength() {
        return this.plateauLength;
    }

    public int getPlateauHeight() {
        return this.plateauHeight;
    }

    public void setPlateauLength(int plateauLength) {
        this.plateauLength = plateauLength;
    }

    public void setPlateauHeight(int plateauHeight) {
        this.plateauHeight = plateauHeight;
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() { return this.yCoordinate; }

    public void setXCoordinate(int xCoordinate) { this.xCoordinate = xCoordinate; }

    public void setYCoordinate(int yCoordinate) { this.yCoordinate = yCoordinate; }

    public OrientationEnum getDirectionFacing() { return this.directionFacing; }

    public void setDirectionFacing(OrientationEnum directionFacing) { this.directionFacing = directionFacing; }


    public Rover(int plateauLength , int plateauHeight) {
        /*
         *
         * Pleateau dimensions must be equal to or greater than 1 for the length anbd height
         * Any other values will result in an invalid plateau ( either negative length or height or 0 length or height, with dimensions set to 0.  Use the default values in property file in this case
         */
        setPlateauLength(( plateauLength >= 1 ) ? plateauLength : Integer.parseInt(Application.getProperties().getProperty("DEFAULT_PLATEAU_LENGTH")));
        setPlateauHeight(( plateauHeight >= 1 ) ? plateauHeight : Integer.parseInt(Application.getProperties().getProperty("DEFAULT_PLATEAU_HEIGHT")));
    }

    public Rover(int x, int y, int orientationIndex, Integer... plateauDimensions) {
        //here plateauDimensions are optional,  but if supplied, only 2 integers will successfully instantiate the object with the plateauDimensions.x and plateauDimensions.y set equal to the 2 options integers
        setXCoordinate(x);
        setYCoordinate(y);
        setDirectionFacing(OrientationEnum.values()[orientationIndex]);
        setPlateauLength((plateauDimensions != null && Arrays.asList(plateauDimensions).size() ==2) ? Arrays.asList(plateauDimensions).get(0).intValue() :  Integer.parseInt(Application.getProperties().getProperty("DEFAULT_PLATEAU_LENGTH")));
        setPlateauHeight((plateauDimensions != null && Arrays.asList(plateauDimensions).size() ==2) ? Arrays.asList(plateauDimensions).get(1).intValue() :  Integer.parseInt(Application.getProperties().getProperty("DEFAULT_PLATEAU_HEIGHT")));
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
                            this.directionFacing = OrientationEnum.values()[((this.directionFacing.ordinal() + 1)% 4)];
                            instrResult =  true;
                            break;
                case "R" :
                            this.directionFacing = OrientationEnum.values()[((this.directionFacing.ordinal() ))].ordinal() >= 1 ?     (OrientationEnum.values()[((this.directionFacing.ordinal() -1 ) % 4)]) :  OrientationEnum.values()[3];
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

        switch ( this.directionFacing.ordinal() ) {

            case 0 :
                     if( this.yCoordinate + 1 <= this.plateauHeight) {
                         this.yCoordinate +=1;
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
                         this.yCoordinate -=1;
                         return true;
                     }
                     break;
            case 3 :
                    if(this.xCoordinate +1 <= this.plateauLength) {
                        this.xCoordinate +=1 ;
                        return true;
                    }
                     break;
        }

        //if made it this far then invalid movement resulted in rover trying to move past the plateau dimensions.  or into negative coordinates

        return false;
    }

    public String printLocation() {
        System.out.println(getXCoordinate() + " " + getYCoordinate() + " " + getDirectionFacing().name() );
        return getXCoordinate() + " " + getYCoordinate() + " " + getDirectionFacing().name();
    }

    @Override
    public String toString() {
        return "Rover{" +
                "xCoordinate=" + getXCoordinate() +
                ", yCoordinate=" + getYCoordinate() +
                ", directionFacing=" + getDirectionFacing().name() +
                ", plateauLength=" + getPlateauLength() +
                ", plateauHeight=" + getPlateauHeight() +
                '}';
    }
}

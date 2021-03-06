package main.java.com.mbw.gpcrover;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class AppController {


    //read input from text file
        // first line is the length and height of the plateau ( upper right position )

        // each rovers input consist of 2 lines
            //1. first line is the rovers position   int int orientation   or int,int,orientation
            //2. second line is a series of instructions for the rover
//    private static int plateauLength = -1;
//    private static int plateauHeight = -1;

//    public int getPlateauLength() {
//        return this.plateauLength;
//    }
//
//    public int getPlateauHeight() {
//        return plateauHeight;
//    }
//
//    public void setPlateauLength(int plateauLength) {
//        this.plateauLength = plateauLength;
//    }
//
//    public void setPlateauHeight(int plateauHeight) {
//        this.plateauHeight = plateauHeight;
//    }

    static int plateauLength = -1;
    static int plateauHeight = -1;
    public static void main(String[] args) {

            ArrayList<Rover> roverArray = new ArrayList<>();
            Properties properties = new Properties();

            try {
                properties.load(new FileInputStream("rover.properties"));

                File file = new File( properties.getProperty("INSTRUCTION_FILE_NAME"));
                Scanner sc = new Scanner(file);

                String line = "";
                boolean DEFINE_PLATAUE = true;
                while (sc.hasNextLine()) {

                    line = sc.nextLine();
                    String[] pieces = line.split("\\s+");

                    if(DEFINE_PLATAUE) {
                            plateauLength = Integer.parseInt(pieces[0]);
                            plateauHeight = Integer.parseInt(pieces[1]);
                            DEFINE_PLATAUE = false;
                    } else {

                        Rover rover = setupNewRover(pieces);
                        if (rover != null) {
                            roverArray.add(rover);
                            rover.processInstructions(sc.nextLine());
                        }
                    }
                }


                for(Rover r : roverArray) {
                    r.printLocation();
                }


            } catch (FileNotFoundException fnfe) {
                System.out.println("Unable to find rover instruction file.   Can not find the following file: " +  properties.getProperty("INSTRUCTION_FILE_NAME"));
            } catch(IOException ioe) {
                System.out.println("IOException trying to read properties file: " + ioe.getMessage());
            }

  }

  private static int encodeOrientation(String orientation) {

        switch (orientation.toUpperCase()) {
            case "N" : return 0;
            case "W" : return 1;
            case "S" : return 2;
            case "E" : return 3;

            default :  return -1;
        }
  }



  public static Rover setupNewRover(String[] startingValues) {
        if(startingValues.length == 3) {

            String x = startingValues[0];
            String y = startingValues[1];
            String orientation = startingValues[2];

            return new Rover(Integer.parseInt(x) , Integer.parseInt(y), encodeOrientation(orientation),  plateauLength,  plateauHeight);

        }

     return null;
  }





}

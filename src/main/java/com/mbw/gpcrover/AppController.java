package main.java.com.mbw.gpcrover;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class AppController {

    private static int plateauLength = -1;
    private static int plateauHeight = -1;
    private static Properties properties = new Properties();
    private static ArrayList<Rover> roverArray = new ArrayList<>();
    public static Properties getProperties() { return properties; }

    public static void setProperties(Properties properties) {

        try {
           getProperties().load(new FileInputStream("rover.properties"));
        } catch(FileNotFoundException fnfe) {
            // add logging
        } catch(IOException ioe) {
            //add loggin
        }
    }


    public static ArrayList<Rover> getRoverArray() {
        return roverArray;
    }

    public static int getPlateauLength() {
        return plateauLength;
    }

    public static void setPlateauLength(int length) {
        plateauLength = (length <= 0 ) ? Integer.parseInt(getProperties().getProperty("DEFAULT_PLATEAU_LENGTH")) : length;
    }

    public static int getPlateauHeight() {
        return plateauHeight;
    }

    public static void setPlateauHeight(int height) {
        plateauHeight = (height <= 0 ) ? Integer.parseInt(getProperties().getProperty("DEFAULT_PLATEAU_HEIGHT")) : height;
    }

    public static void main(String[] args) {

        try {
                setProperties(getProperties());

                File file = new File( getProperties().getProperty("INSTRUCTION_FILE_NAME"));
                Scanner sc = new Scanner(file);

                String line;
                boolean DEFINE_PLATEAU = true;
                while (sc.hasNextLine()) {

                    line = sc.nextLine();
                    String[] pieces = line.split("\\s+");

                    if(DEFINE_PLATEAU) {
                         setPlateauLength(Integer.parseInt(pieces[0]));
                         setPlateauHeight(Integer.parseInt(pieces[1]));
                         DEFINE_PLATEAU = false;
                    } else {
                        Rover rover = setupNewRover(pieces);
                        if (rover != null) {
                            getRoverArray().add(rover);
                            rover.processInstructions(sc.nextLine());
                        }
                    }
                }

                for(Rover rover : getRoverArray()) {
                    rover.printLocation();
                }

          }catch(IOException ioe) {
                System.out.println("IOException trying to read properties file: " + ioe.getMessage());
          }
  }


  private static Rover setupNewRover(String[] startingValues) {
        if(startingValues.length == 3) {
            return new Rover(Integer.parseInt(startingValues[0]) , Integer.parseInt(startingValues[1]), OrientationEnum.valueOf(startingValues[2]).ordinal(),  getPlateauLength(), getPlateauHeight());
        }

     return null;
  }





}

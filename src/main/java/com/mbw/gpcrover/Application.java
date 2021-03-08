package main.java.com.mbw.gpcrover;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static Logger logger = Logger.getLogger(String.valueOf(Application.class));

    private  int plateauLength = -1;
    private  int plateauHeight = -1;
    private  static Properties properties = new Properties();
    private  ArrayList<Rover> roverArray = new ArrayList<>();
    private Scanner scanner;

    public static Properties getProperties() {

        return new Properties(properties);
    }

    private void setProperties(Properties properties) {

        try {
           properties.load(new FileInputStream("rover.properties"));
        } catch(FileNotFoundException fnfe) {
            logger.log(Level.SEVERE ,"FileNotFoundException trying to read rover.properties", fnfe);
        } catch(IOException ioe) {
            logger.log(Level.SEVERE ,"IOException trying to read rover.properties", ioe);
        }
    }


    public ArrayList<Rover> getRoverArray() {
        return new ArrayList<Rover>(this.roverArray);
    }

    public int getPlateauLength() {
        return this.plateauLength;
    }

    public void setPlateauLength(int length) {
        this.plateauLength = (length <= 0 ) ? Integer.parseInt(properties.getProperty("DEFAULT_PLATEAU_LENGTH")) : length;
    }

    public int getPlateauHeight() {
        return this.plateauHeight;
    }

    public void setPlateauHeight(int height) {
        this.plateauHeight = (height <= 0 ) ? Integer.parseInt(properties.getProperty("DEFAULT_PLATEAU_HEIGHT")) : height;
    }

    private void loadPropertiesFile() {
        setProperties(properties);
    }

    private void initializePlateau() {
        if(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.split("\\s+");
            this.plateauLength = Integer.parseInt(pieces[0]);
            this.plateauHeight = Integer.parseInt(pieces[1]);
        }
    }

    private void initializeScanner() {
        try {
            this.scanner = new Scanner(new File(getProperties().getProperty("INSTRUCTION_FILE_NAME")));
        } catch(FileNotFoundException fnfe) {
            logger.log(Level.SEVERE ,"FileNotFoundException trying to read INSTRUCTION_FILE_NAME from Properties", fnfe);
        }
    }


    private Rover setupNewRover(String[] startingValues) {
        if(startingValues.length == 3) {
            return new Rover(Integer.parseInt(startingValues[0]) , Integer.parseInt(startingValues[1]), OrientationEnum.valueOf(startingValues[2]).ordinal(), this.plateauLength, this.plateauHeight);
        }
        logger.log(Level.WARNING, "setupNewRover failed trying to instantiate Rover with something other than 3 values for startingValues array");
        return null;
    }

    private void processRoverInstructions() {

        while (scanner.hasNextLine()) {
            String[] pieces = scanner.nextLine().split("\\s+");
            Rover rover = setupNewRover(pieces);
            if (rover != null) {
                roverArray.add(rover);
                rover.processInstructions(scanner.nextLine());
            }
        }
    }

    private void printRoverLocations() {
        for(Rover rover : getRoverArray()) {
            rover.printLocation();
        }
    }

    protected void run(String[] args) {
            loadPropertiesFile();
            initializeScanner();
            initializePlateau();
            processRoverInstructions();
            printRoverLocations();
    }

    protected Application() { }

    public static void main(String[] args) {
        Application application = new Application();
        application.run(args);
  }
}

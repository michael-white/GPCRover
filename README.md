# GPCRover

Michael White

I added a properties file in the root of this project.
These are the only properties: 
  DEFAULT_PLATEAU_LENGTH=10;
  DEFAULT_PLATEAU_HEIGHT=10;
  INSTRUCTION_FILE_NAME=rover_instructions.txt

Either change the INSTRUCTION_FILE_NAME to your file name or change your file name to rover_instructions.txt and be sure that the file is 
placed in the root of the project.

I've included rover_instructions.txt in the location any subsequent commands / instructions are to be placed for more rovers and more testing.  This
file currently only contains the sample input provided with the problem description.

I tried to validate the input and verify the expected states as best I could.

Because there isnt supposed to be any output,  except the printing of the rover's final position,  
the errors and output from exceptions, etc. will be written to a log file instead of stdin.

I also have included a test class for the Rover class and the core methods that are used with it.

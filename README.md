### GPCRover Project, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; michael white  ( michael.white.b@gmail.com)  

<br>
I added a properties file (**rover.properties**) in the root of this project.
these are the only properties: 

  - **DEFAULT_PLATEAU_LENGTH=10**
  - **DEFAULT_PLATEAU_HEIGHT=10**
  - **INSTRUCTION_FILE_NAME=rover_instructions.txt**
    - either change the **INSTRUCTION_FILE_NAME** value to your file name 
    - or change your file name to **rover_instructions.txt** and be sure that the file is 
placed in the root of the project.

i've included **rover_instructions.txt** in the location any subsequent commands / instructions are to be placed for more rovers and more testing.  this
file currently only contains the sample input provided with the problem description.

i tried to validate the input and verify the expected states as best i could.
i added some UnitTests in **RoverTests.java** that verifies the main functionality of the project, independently from the other functionality

Because the printing of each rover's final position is the only output specified,   
the errors and output from exceptions, etc. will be written to a log file instead of stdout.

I made the project be **gradle** based instead of **maven**,  primarily for the gradlew file that will run everything for you without having to define a project and compile and run it manually.

Once you have cloned or downloaded the repository from github,  from the project root,  
  - copy or paste your file that contains the plateau dimensions line and rover lines to the project root and either 
    - rename your file to **rover_instructions.txt**
    - or change the value of **INSTRUCTION_FILE_NAME** ( in GPCRover/rover.properties ) to whatever you've named your file.
  - run the application and tests from the project root with 
    - **gradlew run**
    - test results will be in **build\test-results\test** 
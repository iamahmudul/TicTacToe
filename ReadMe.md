# TicTacToe Game: How to package

## Create a manifest file MANIFEST.mf
```
Manifest-Version: 1.0
Main-Class: TicTacToe

```

## Compile the java class
javac TicTacToe.java Game.java Board.java Player.java GameStatus.java

## Create a package
"C:\Program Files\Java\jdk-17\bin\jar" cfm TicTacToeGame.jar MANIFEST.MF *.class

## Run the game from compiler
java -jar TicTacToeGame.jar

## Create an Executable for Windows (.exe)
Download Launch4J
Open Launch4J.
Set the Output file to TicTacToeGame.exe (or any name you prefer).
Set the Jar file to the path of your JAR file (e.g., TicTacToeGame.jar).
Set the Main class as TicTacToe (the class containing the main method).
Optionally, set an icon file if you want a custom icon for your executable.
Click the Build wrapper button

## (Optional) Distribute as a Self-Contained Application
create a self-contained application by bundling the JRE
```
"C:\Program Files\Java\jdk-17\bin\jpackage" --input . --name TicTacToeGame --main-jar TicTacToeGame.jar --main-class TicTacToe --type app-image
```

Use command prompt for these commands.
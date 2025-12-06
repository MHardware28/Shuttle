# CAMPUS SHUTTLE TRACKER
This project is a Java-based shuttle tracker simulation designed to demonstrate the use of multiple object-oriented design patterns working together.
The system simulates shuttle movement through various states, updates a graphical interface, and allows users to select shuttle types and control the simulation through a facade.
---
## Features
State Pattern:
- Manages shuttle behavior using ArrivingState, LeavingState, and EnrouteState, controlled through ShuttleContext.

Singleton + Factory Method Pattern:
- Creates different shuttle types (DayShuttle, NightShuttle, WeekendShuttle) using both a simple factory and specialized ShuttleCreator subclasses.
- The singleton ensures theres only one instance of the shuttle in the program.

Observer Pattern:
- The ShuttleObserver notifies all registered observers (including ShuttleGUI) whenever shuttle status or location changes.

Facade Pattern:
- ShuttleFacade provides a simplified interface for starting/stopping simulations, selecting shuttle types, and managing system interactions.

GUI Integration:
- A Swing-based interface (ShuttleGUI) allows users to visualize shuttle movement and interact with the simulation.

---
## System Overview
- ShuttleService for managing routes and stops
- Route and Stop classes representing shuttle paths
- ShuttleContext for state transitions
- ShuttleObserver for notifying users
- Singleton for creating the single instance of the shuttle
- A facade to connect all backend logic to the GUI

---
## Timeline
Oct 28, 2025 – Initial ideas
Nov 13, 2025 – Project proposal
Nov 30, 2025 – First draft
Dec 2, 2025 – Final adjustments
Dec 4, 2025 -  Final Project Demo

---
## How to Run
- Download zip file from the MASTER branch, open in Intellij
- Compile all project classes.
- Run ShuttleGUI to launch the interface.
- Choose a shuttle type and begin the simulation.

---
## Front Page
![IMG_7345](https://github.com/user-attachments/assets/7c4d36d6-e325-407e-8afa-c6746b8e9c3b)
---
## UML
<img width="1029" height="1119" alt="ShuttleDiagram" src="https://github.com/user-attachments/assets/ae0c8818-b4f5-4752-bae8-3d08be0a7e7f" />

## Youtube Link
> https://youtu.be/okRsi-AvZrQ


## Resources
Multiple screens?
https://stackoverflow.com/questions/26962678/java-multiple-screens?newreg=675bf194b271430ea0184de410ba4af2

Background color?



https://stackoverflow.com/questions/1081486/setting-background-color-for-a-jframe

Color types available?

https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html

Rgb correspondence? 

https://www.rapidtables.com/web/color/RGB_Color.html

Font>?
https://docs.oracle.com/javase/8/docs/api/java/awt/Font.html

Image icon:
https://stackoverflow.com/questions/13011705/how-to-add-an-imageicon-to-a-jframe

Simulation {

https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map/46908#46908 


https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Random.html


https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TimerTask.html


 https://stackoverflow.com/questions/4044726/how-to-set-a-timer-in-java


https://coderanch.com/t/650127/java/create-simple-random-number-generator

}

Lambda filtering:
https://stackoverflow.com/questions/30012295/java-8-lambda-filter-by-lists

Class slides on ALL patterns used:

https://docs.google.com/presentation/d/1c0Eg8KsUat0NSZCqD-7ELJH6ARQ6DosOCHpzKI3oihg/edit?slide=id.g2d3cc6a17d5_0_379#slide=id.g2d3cc6a17d5_0_379

https://docs.google.com/presentation/d/1cO-c3jyNb6txVp303hjSZfXtsBVe28NV-QqIZNfFDHQ/edit?slide=id.g39b3c650e7d_0_83#slide=id.g39b3c650e7d_0_83

https://docs.google.com/presentation/d/11hlfDp6qtIUegHK5Gr3EqrwJN9Rg0KQa1lEz1IhnNBk/edit?slide=id.g2d3a466e36d_0_78#slide=id.g2d3a466e36d_0_78

https://docs.google.com/presentation/d/1Wkj1tmX4K-Jv8D9NpA3jBcSw4bp6H-oBhuopkNIp15I/edit?slide=id.g37d34141a24_0_0#slide=id.g37d34141a24_0_0

https://docs.google.com/presentation/d/1XLVoO6Ttg_8uS501ywdIPuONW2cCtwg_Jm6tufaIgHI/edit?slide=id.g36523decf7d_0_0#slide=id.g36523decf7d_0_0



  

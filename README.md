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
- Download zip file, open in Intellij
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
> 


  

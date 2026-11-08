# Traffic Management System

A Java coursework application modelling traffic systems, sensors and signals, with Swing management screens and SQLite persistence.

## Features and structure

- Separate model, data-access and Swing view packages.
- Management screens for traffic systems, sensors and signals.
- SQLite persistence with foreign-key relationships.
- Classes illustrating factory, decorator, composite and observer concepts.
- Validation and a custom invalid-traffic-data exception.

## Technologies

Java, Swing, JDBC, SQLite and Maven. The Maven project targets Java 18.

## Getting started

1. Install a JDK compatible with the Java 18 compiler target, Maven and the SQLite command-line tool.
2. From this repository root, initialise an empty database: `sqlite3 traffic.db < schema.sql`.
3. Open the Maven project in NetBeans or another Java IDE and resolve its dependencies.
4. Build with `mvn compile`.
5. Run `TrafficManagementSystem.view.MainGUI` to launch the Swing interface. Keep the working directory at the repository root so it uses the database created in step 2.

The POM's configured main class, `com.mycompany.trafficmanagementsystem.TrafficManagementSystem`, is a separate console demonstration; it does not open the GUI.

## Limitations

This is a classroom simulation and record-management app, not a connection to live traffic infrastructure. The original database records and compiled output are excluded; `schema.sql` preserves the original database structure without records.

## Project context

Group academic project. Feature descriptions refer to the complete team project.

## My contribution

I contributed to development alongside my teammates and reviewed their code to check consistency and how the components worked together.

Specific component ownership and named collaborator credits are not listed yet.

## Validation status

Source reviewed for portfolio preparation; the application has not been built or run as part of this preparation. Screenshots and a tested demo walkthrough are still to be added.

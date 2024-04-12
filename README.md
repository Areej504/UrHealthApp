# UrHealthApp: A Health and Fitness Club Management System - COMP3005

## Overview

UrHealthApp serves as a Desktop portal for managing various aspects of a Health and Fitness Club. 
UrHealthApp uses a PostgreSQL relational database to store user data and booking information, as well as the Java.swing library to implement a functional graphical user interface.
The project uses the Model-View-Controller (MVC) Design Pattern to separate application logic (User functionalities and database connection) from the user interface (User Views and Login UI).

## Getting Started

To run this project, ensure you have Java installed on your system along with the PostgreSQL JDBC driver. You'll also need access to a PostgreSQL database named `UrHealthApp`.
Run the file SQL/DDL.sql in PostgreSQL to create the tables, then run the file SQL/DML.sql to populate them with some sample data.

## Usage
1. Download or clone the Github repository at https://github.com/Areej504/UrHealthApp.git

2. Set Up Database Connection: Modify the `main` method in the `DBConnection` class to specify your database URL, username, and password.

3. Run the Application: Compile and run the `DBConnection` class. This will establish a connection to your database and launch the Login UI.

## Functionality
- Members can register and manage their profiles effortlessly. They have the autonomy to establish personalized fitness goals, input health metrics, and track their progress over time. 
A personalized dashboard offers insights into exercise routines, fitness achievements, and health statistics. Additionally, members can book available personal sessions or group classes with certified trainers, and cancel
previously booked sessions.
- Trainers can manage their schedules efficiently by adding personal sessions or group classes to their availability and access 
member profiles to tailor fitness programs according to individual needs and preferences.
- Admin staff have access to managing room bookings, monitoring fitness equipment maintenance, updating class schedules and overseeing billing. 
The system aims to simplify administrative tasks and ensure smooth operations of the club.

## Project Structure

The project is organized into the following classes:

- `DBConnection`: This class is executed to launch the application. This class initializes the Database connection through PostgreSQL and launches the LoginUI and user views.

- `User`: This is the superclass for Member, Trainer and AdminStaff. It implements some shared functions and stores the database connection and the current user’s email.

- `LoginUI`: This class presents the Login dialog which is the first thing the user sees. This includes user registration and existing user authentication functions.

- `Member`: Model class for the member view, implements member functions such as class booking, dashboard display and updating the member profile.

- `MemberView`: implements the member GUI display and dashboard using java.swing

- `MemberController`: listens for MemberView input and invokes model functionality

- `Trainer`:  Model class for the member view, implement member functions such as adding personal session and group class availability, and search member profiles.

- `TrainerView`: implements the trainer GUI display and dashboard using java.swing

- `TrainerController`:  listens for TrainerView input and invokes model functionality

- `AdminStaff`: Model class for AdminStaff, implements admin functionality such as changing room bookings, updating class schedules, cancelling sessions and monitoring equipment.

- `AdminView`: implements the Admin GUI display and dashboard using java.swing

- `AdminController`:  listens for AdminView input and invokes model functionality

## Known Issues
The following known issues were not fixed due to the time constraint of this project, but can be fixed in future iterations.

- The application does not implement passwords, however this can easily be added to the DB tables and LoginUI.
- LoginUI proceeds with displaying the email prompt and Member view even if the dialog box is cancelled.
- Routines are not removed from a member's dashboard if the member cancels the corresponding booking.
- No error messages are displayed to the view if a SQL exception occurs when retrieving data.
- Admin staff can only view billings since the project does not actually integrate with a payment service.

## Dependencies

- PostgreSQL JDBC Driver

## Video Demonstration
Watch the Video below for a live demo of the UrHealthApp project's functionality!

## Author
Areej Mahmoud 101218260






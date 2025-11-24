# Task Management System - Java CLI

## About
This is a simple command line interface (CLI) application to manage tasks with user registration and login functionality. Users can add, view, update, and delete their tasks. The system also provides basic productivity analytics like completion rates and task status counts.

## Features
- User account registration and login
- Add new tasks with title, description, due date, priority, and status
- View list of tasks belonging to the logged-in user
- Update task status (Pending or Completed)
- View productivity report showing completed and pending tasks, and completion percentage
- Data persistence using text files (`users.txt` and `tasks.txt`)

## Installation and Setup
1. Ensure you have Java JDK 8 or higher installed.
2. Clone or download the project files and place them in a directory.
3. Navigate to the project root directory in your command prompt or terminal.
4. Compile the Java files:
javac Main.java model*.java service*.java db*.java util*.java
5. Run the program: java Main


## Usage
- On running the program, you will see options to register or login.
- Register a new user or log in with existing credentials.
- Use the menu options to manage your tasks and view productivity.
- Your data will be saved automatically upon exiting the program.

## File Structure
TaskManager/
├── model/
│ ├── User.java
│ └── Task.java
├── service/
│ ├── UserService.java
│ ├── TaskService.java
│ └── AnalyticsService.java
├── db/
│ └── FileDatabase.java
├── util/
│ └── InputHelper.java
├── Main.java
├── users.txt
└── tasks.txt





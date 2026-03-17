# Campus Resource Booking & Approval System  
CST8288 – Group Project 3 (Second Iteration)

## Overview
This repository contains the second iteration of a web-based booking system designed for campus resource management. The project follows a three-tier architecture:

- **Presentation Layer:** Java Servlets (Web Browser)  
- **Business Logic Layer:** Java classes implementing design patterns (Builder, State, Strategy, etc.)  
- **Data Layer:** JDBC connection to a MySQL database  

## Key Features
- Booking Submission: Users can submit booking requests through a web form  
- View Bookings: Display all bookings stored in the database  
- Role-Based Logic: Approval behavior varies based on user role (Student, Staff, Manager)  
- State Management: Booking lifecycle handled using State Pattern (Requested, Approved, Denied, Cancelled)  
- Design Patterns: Implementation includes Builder, State, and Strategy patterns  
- Database Integration: Uses JDBC to connect and interact with MySQL  

## Setup Instructions

### Clone the repository
Use git clone to download the source code.

### Import into Eclipse
- Import the project into Eclipse as a Dynamic Web Project.

### Set up the database
Run database.sql (DDL file) to create the database, tables, and insert sample data.

### Configure database connection
Open DBConnection.java and update the database credentials if needed.

- Example:  
  URL: jdbc:mysql://localhost:3306/campusbooking  
  Username: root  
  Password: your_password  

- Note  
  Make sure the username and password match your local MySQL setup.  
  If the connection fails, double check your database name, username, and password.  

### Deploy
Run the project on Tomcat 9 or deploy using the exported .war file.

### Access the application
Submit booking: http://localhost:8081/CampusBookingProject/submit  
View bookings: http://localhost:8081/CampusBookingProject/bookings  

- Note  
  If your Tomcat server is running on a different port, replace 8081 with your port number (for example 8080).  

## Notes
- The database must be created before running the application.  
- Sample data is included to avoid foreign key errors.  
- If issues occur, try cleaning the project and restarting Tomcat.  

## Team Members
- Samar  
- Travis Lepage  
- Nabil Aitbelkas 

# Campus Resource Booking & Approval System  
**CST8288 – Group Project 2 (Initial Implementation / MVP)**

## Overview
This repository contains the first iteration of a secure, web-based booking system designed for campus resource management. The project follows a three-tier architecture:

1. **Presentation Layer:** Java Servlets (Web Browser)
2. **Business Logic Layer:** Java classes implementing design patterns (Builder, State, Strategy, etc.)
3. **Data Layer:** JDBC connection to a centralized database

## Key MVP Features
- **User Authentication:** Login and registration with roles (Student, Staff, Manager)
- **Resource Browsing:** Search and view campus equipment and rooms
- **Booking Workflow:** Submit booking requests with conflict validation to prevent double-booking
- **Approvals:** Management interface for approving or denying requests

## Setup Instructions
1. **Clone the repository**
   - Use `git clone` to download the source code.
2. **Import into Eclipse**
   - Import the project into Eclipse as a **Dynamic Web Project**.
3. **Set up the database**
   - Run `database.sql` (DDL file) to create the required tables.
4. **Deploy**
   - Deploy to **Tomcat 9** using the exported `.war` file.

## Team Members
- **Samar**
- **Travis Lepage**
- **Nabil Aitbelkas**

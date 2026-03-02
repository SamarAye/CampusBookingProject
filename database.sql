-- Database Schema for Campus Resource Booking
-- Data Layer (Tier 3)

-- 1. Create Users Table (Handles PR0003: Student, Staff, Manager roles)
CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL 
);

-- 2. Create Resources Table (Handles PR0005: Resource details)
CREATE TABLE Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50), -- e.g., 'Study Room' or 'Laptop'
    is_available BOOLEAN DEFAULT TRUE
);

-- 3. Create Bookings Table (Handles PR0008: Booking submission)
CREATE TABLE Bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    resource_id INT,
    status VARCHAR(20) DEFAULT 'Requested', -- e.g., 'Approved', 'Denied'
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (resource_id) REFERENCES Resources(resource_id)
);
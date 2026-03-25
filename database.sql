CREATE DATABASE IF NOT EXISTS campusbooking;
USE campusbooking;

-- Users table
CREATE TABLE IF NOT EXISTS Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Resources table
CREATE TABLE IF NOT EXISTS Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    is_available BOOLEAN DEFAULT TRUE
);

-- Bookings table
CREATE TABLE IF NOT EXISTS Bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    resource_id INT,
    status VARCHAR(20) DEFAULT 'Requested',
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (resource_id) REFERENCES Resources(resource_id)
);
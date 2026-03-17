CREATE DATABASE IF NOT EXISTS campusbooking;
USE campusbooking;

-- USERS TABLE
CREATE TABLE IF NOT EXISTS Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL 
);

-- RESOURCES TABLE
CREATE TABLE IF NOT EXISTS Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    is_available BOOLEAN DEFAULT TRUE
);

-- BOOKINGS TABLE
CREATE TABLE IF NOT EXISTS Bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    resource_id INT,
    status VARCHAR(20) DEFAULT 'Requested',
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (resource_id) REFERENCES Resources(resource_id)
);

-- =========================
-- SAMPLE DATA (IMPORTANT)
-- =========================

-- Users
INSERT INTO Users (username, password, role) VALUES
('student1', 'pass123', 'Student'),
('staff1', 'pass123', 'Staff'),
('manager1', 'pass123', 'Manager');

-- Resources
INSERT INTO Resources (name, type, is_available) VALUES
('Study Room A', 'Room', TRUE),
('Laptop 1', 'Equipment', TRUE);

-- Optional: sample booking (can remove if you want clean start)
INSERT INTO Bookings (user_id, resource_id, status) VALUES
(1, 1, 'Requested');
-- Crime Management System Database Schema
-- Run this script in MySQL Workbench or command line to set up the database

-- Create database
CREATE DATABASE IF NOT EXISTS crime_management;
USE crime_management;

-- Create Officer table
CREATE TABLE IF NOT EXISTS officer (
    OfficerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    `Rank` VARCHAR(50),
    Phone VARCHAR(20),
    INDEX idx_name (FirstName, LastName)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Create Case table
CREATE TABLE IF NOT EXISTS `case` (
    CaseID INT AUTO_INCREMENT PRIMARY KEY,
    CaseDetails TEXT NOT NULL,
    AttendingOfficer INT NOT NULL,
    Category VARCHAR(100),
    Location VARCHAR(255),
    ReportedOn DATE,
    Status VARCHAR(50),
    FOREIGN KEY (AttendingOfficer) REFERENCES officer(OfficerID) ON DELETE RESTRICT ON UPDATE CASCADE,
    INDEX idx_officer (AttendingOfficer),
    INDEX idx_status (Status),
    INDEX idx_date (ReportedOn)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Sample data (optional - remove if not needed)
-- INSERT INTO officer (FirstName, LastName, `Rank`, Phone) VALUES
-- ('John', 'Smith', 'Detective', '555-1234'),
-- ('Sarah', 'Johnson', 'Sergeant', '555-5678'),
-- ('Michael', 'Brown', 'Officer', '555-9012');

-- INSERT INTO `case` (CaseDetails, AttendingOfficer, Category, Location, ReportedOn, Status) VALUES
-- ('Vehicle theft reported at shopping center', 1, 'Theft', '123 Main St', '2024-01-15', 'Open'),
-- ('Burglary at residential address', 2, 'Burglary', '456 Oak Ave', '2024-01-16', 'Under Investigation'),
-- ('Assault incident downtown', 3, 'Assault', '789 Pine St', '2024-01-17', 'Closed');

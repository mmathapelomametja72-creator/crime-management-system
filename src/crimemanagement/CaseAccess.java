/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crimemanagement;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author mmath
 */
public class CaseAccess {
   
    // Will add new case record
public void addCase(Connection conn, Scanner input) {
    try {
        System.out.print("Enter Case Details: ");
        String details = input.nextLine();

        System.out.print("Enter Attending Officer Name (First and Last): ");
        String officerName = input.nextLine().trim();

        System.out.print("Enter Category: ");
        String category = input.nextLine();
        System.out.print("Enter Location: ");
        String location = input.nextLine();

        // New input for reported date
        System.out.print("Enter Date Reported (YYYY-MM-DD): ");
        String reportedOn = input.nextLine();

        System.out.print("Enter Status: ");
        String status = input.nextLine();

        //  name into first and last
        String[] parts = officerName.split(" ", 2);
        String firstName = parts[0];
        String lastName = (parts.length > 1) ? parts[1] : "";

        // Find the officer ID
        String findOfficer = "SELECT OfficerID FROM officer WHERE FirstName = ? AND LastName = ?";
        PreparedStatement findPs = conn.prepareStatement(findOfficer);
        findPs.setString(1, firstName);
        findPs.setString(2, lastName);
        ResultSet rs = findPs.executeQuery();

        int officerID = -1;

        if (rs.next()) {
            officerID = rs.getInt("OfficerID");
        } else {
            // Add new officer if not found
            String insertOfficer = "INSERT INTO officer (FirstName, LastName, `Rank`, Phone) VALUES (?, ?, 'Unknown', 'N/A')";
            PreparedStatement insertPs = conn.prepareStatement(insertOfficer, Statement.RETURN_GENERATED_KEYS);
            insertPs.setString(1, firstName);
            insertPs.setString(2, lastName);
            insertPs.executeUpdate();

            ResultSet newRs = insertPs.getGeneratedKeys();
            if (newRs.next()) {
                officerID = newRs.getInt(1);
            }
        }

        //Insert the case record
        String sql = "INSERT INTO `case` (CaseDetails, AttendingOfficer, Category, Location, ReportedOn, Status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, details);
        ps.setInt(2, officerID);
        ps.setString(3, category);
        ps.setString(4, location);
        ps.setString(5, reportedOn); 
        ps.setString(6, status);
        ps.executeUpdate();

        System.out.println("Case added successfully!");

    } catch (SQLException e) {
        System.out.println("Error adding case: " + e.getMessage());
    }
}



//View Cases
public void viewCases(Connection conn) {

    try {
        String sql = "SELECT c.CaseID, c.CaseDetails, CONCAT(o.FirstName, ' ', o.LastName) AS OfficerName, " +
                     "c.Category, c.Location, c.ReportedOn, c.Status " +
                     "FROM `case` c INNER JOIN officer o ON c.AttendingOfficer = o.OfficerID";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("Case Records");
        while (rs.next()) {
            System.out.printf(
                "ID: %d | Details: %s | Officer: %s | Category: %s | Location: %s | Date: %s | Status: %s%n",
                rs.getInt("CaseID"),
                rs.getString("CaseDetails"),
                rs.getString("OfficerName"),
                rs.getString("Category"),
                rs.getString("Location"),
                rs.getDate("ReportedOn"),
                rs.getString("Status")
            );
        }

    } catch (SQLException e) {
        System.out.println("Error viewing cases: " + e.getMessage());
    }
}

// Update case
public void updateCase(Connection conn, Scanner input) {
    try {
        System.out.print("Enter Case ID to update: ");
        int id = input.nextInt();
        input.nextLine(); // consume newline

        System.out.print("Enter new Status: ");
        String newStatus = input.nextLine();

        System.out.print("Enter new Location: ");
        String newLocation = input.nextLine();

        String sql = "UPDATE `case` SET Status = ?, Location = ? WHERE CaseID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, newStatus);
        ps.setString(2, newLocation);
        ps.setInt(3, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("Case updated successfully!");
        } else {
            System.out.println("Case not found with ID: " + id);
        }

    } catch (SQLException e) {
        System.out.println("Error updating case: " + e.getMessage());
    }
}
}

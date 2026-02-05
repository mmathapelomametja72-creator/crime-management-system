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
public class OfficerAccess {

    // Will add a new officer record
    public void addOfficer(Connection conn, Scanner input) {
        try {
            System.out.print("Enter First Name: ");
            String firstName = input.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = input.nextLine();
            System.out.print("Enter Rank: ");
            String rank = input.nextLine();
            System.out.print("Enter Phone: ");
            String phone = input.nextLine();

            String sql = "INSERT INTO officer (FirstName, LastName, `Rank`, Phone) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, rank);
            ps.setString(4, phone);
            ps.executeUpdate();

            System.out.println("Officer added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding officer: " + e.getMessage());
        }
    }

    // Viewing all of the officers
    public void viewOfficers(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM officer");

            System.out.println("Officer Records");
            while (rs.next()) {
                System.out.printf("ID: %d | %s %s | Rank: %s | Phone: %s%n",
                        rs.getInt("OfficerID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Rank"),
                        rs.getString("Phone"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing officers: " + e.getMessage());
        }
    }

    //Updates the officer phone number
  
        public void updateOfficer(Connection conn, Scanner input) {
            try {
                System.out.print("Enter Officer ID to update: ");
                int id = input.nextInt();
                input.nextLine(); // consume newline

                System.out.print("Enter new phone number: ");
                String newPhone = input.nextLine();

                String sql = "UPDATE officer SET Phone=? WHERE OfficerID=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, newPhone);
                ps.setInt(2, id);

        int rows = ps.executeUpdate();
 
        if (rows > 0) {
            System.out.println("Officer was updated successfully!");
        } else {
            System.out.println("Officer ID not found.");
        }

    } catch (SQLException e) {
        System.out.println("Error updating officer information: " + e.getMessage());
    }
}

    
}

    


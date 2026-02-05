/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crimemanagement;
import java.sql.*;
import java.util.Scanner;


/**
 *
 * @author mmath
 */
public class CrimeManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = DBConnection.connect();
        Scanner input = new Scanner(System.in);
        OfficerAccess officerAccess = new OfficerAccess(); 
        CaseAccess caseAccess = new CaseAccess();

        int choice;
        do {
            System.out.println("Welcome to Crime Management ");
            System.out.println("1. Add New Officer Record");
            System.out.println("2. View Officer Records");
            System.out.println("3. Update Officer Record");
            System.out.println("4. Add New Case Record");
            System.out.println("5. View Case Records");
            System.out.println("6. Update Case Record");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();
            

         switch (choice) {
                case 1:
                    officerAccess.addOfficer(conn, input);
                    break;
                case 2:
                    officerAccess.viewOfficers(conn);
                    break;
                case 3:
                    officerAccess.updateOfficer(conn, input);
                    break;
                case 4:
                    caseAccess.addCase(conn, input);
                    break;
                case 5:
                    caseAccess.viewCases(conn);
                    break;
                case 6:
                    caseAccess.updateCase(conn, input);
                    break;
                case 7:
                    System.out.println("Exiting program");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 7);
    }
}
 
    

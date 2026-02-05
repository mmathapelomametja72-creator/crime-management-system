/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crimemanagement;
import java.sql.Date;
/**
 *
 * @author mmath
 */
public class CaseRecords {
    
    private int CaseID;
    private String CaseDetails;
    private int AttendingOfficer;
    private String Category;
    private String Location;
    private Date ReportedOn;
    private String Status;

    // Getters and Setters
    public int getCaseID() { return CaseID; }
    public void setCaseID(int CaseID) { this.CaseID = CaseID; }

    public String getCaseDetails() { return CaseDetails; }
    public void setCaseDetails(String CaseDetails) { this.CaseDetails = CaseDetails; }

    public int getAttendingOfficer() { return AttendingOfficer; }
    public void setAttendingOfficer(int AttendingOfficer) { this.AttendingOfficer = AttendingOfficer; }

    public String getCategory() { return Category; }
    public void setCategory(String Category) { this.Category = Category; }

    public String getLocation() { return Location; }
    public void setLocation(String Location) { this.Location = Location; }

    public Date getReportedOn() { return ReportedOn; }
    public void setReportedOn(Date ReportedOn) { this.ReportedOn = ReportedOn; }

    public String getStatus() { return Status; }
    public void setStatus(String Status) { this.Status = Status; }
}

    


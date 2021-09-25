/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dylco
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;


public class WaitlistQueries {
    private static Connection connection;
    private static ArrayList<WaitlistEntry> waitlistByDate = 
            new ArrayList<>();
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement addWaitlistEntry;
    private static ResultSet resultSet;
    
    public static void addWaitlistEntry(
            String faculty, Date date, int seats, Timestamp timeStamp){
        connection = DBConnection.getConnection();
        
        try{
            addWaitlistEntry = connection.prepareStatement(
                "insert into waitlist (faculty, date, seats, timestamp) values (?,?,?,?)");
            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setDate(2, date);
            addWaitlistEntry.setInt(3, seats);
            addWaitlistEntry.setTimestamp(4, timeStamp);
            addWaitlistEntry.executeUpdate();
            
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(){
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistByDate = new ArrayList<>();
        
        try{
            getWaitlistByDate = connection.prepareStatement(
                    "select faculty, date, seats, timestamp from waitlist order by date, timestamp");
            resultSet = getWaitlistByDate.executeQuery();
            
            while (resultSet.next()){
                WaitlistEntry aWaitlist = new WaitlistEntry(
                        resultSet.getString(1), resultSet.getDate(2), 
                        resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlistByDate.add(aWaitlist);
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return waitlistByDate;
    }
}
    
    


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
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ReservationQueries {
    private static Connection connection;
    private static ArrayList<ReservationEntry> reservationsByDate = 
            new ArrayList<>();
    private static ArrayList<String> roomsAtCertainDate = 
            new ArrayList<>();
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getRoomsAtCertainDate;
    private static PreparedStatement addReservationEntry;
    private static ResultSet resultSet;
    
    public static void addReservationEntry(
            String faculty, String room, Date date, int seats, 
            Timestamp timestamp){
        
        connection = DBConnection.getConnection();
        
        try{
            addReservationEntry = connection.prepareStatement(
            "insert into reservations (faculty, room, date, seats, timestamp) "
                    + "values (?,?,?,?,?)");
            addReservationEntry.setString(1, faculty);
            addReservationEntry.setString(2, room);
            addReservationEntry.setDate(3, date);
            addReservationEntry.setInt(4, seats);
            addReservationEntry.setTimestamp(5, timestamp);
            addReservationEntry.executeUpdate();
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    
    public static ArrayList<ReservationEntry> getReservationsByDate(){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationsByDate = new ArrayList<>();
        
        //returns the reservations ordered by date
        
        try{
            getReservationsByDate = connection.prepareStatement(
                    "select faculty, room, date, seats, timestamp from reservations order by date");
            
            resultSet = getReservationsByDate.executeQuery();
            //loop through resultSet and add each reservation to the list
            while(resultSet.next()){
                ReservationEntry aReservation = new ReservationEntry(
                        resultSet.getString(1), resultSet.getString(2), 
                        resultSet.getDate(3), resultSet.getInt(4), 
                        resultSet.getTimestamp(5));
                reservationsByDate.add(aReservation);
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return reservationsByDate;
    }
    
    

    
    
    public static ArrayList<String> getRoomsAtCertainDate(Date selectedDate){
        connection = DBConnection.getConnection();
        ArrayList<String> roomsAtCertainDate = new ArrayList<>();
        
        //returns rooms reserved on certain date
        
        try{
            getRoomsAtCertainDate = connection.prepareStatement(
                    "SELECT room FROM reservations WHERE date = (?) order by date ASC");
            getRoomsAtCertainDate.setDate(1, selectedDate);
            resultSet = getRoomsAtCertainDate.executeQuery();
            //loop through resultSet and add each room to the list
            while(resultSet.next()){
                roomsAtCertainDate.add(resultSet.getString(1));
            }
            
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return roomsAtCertainDate;
    }
    
}

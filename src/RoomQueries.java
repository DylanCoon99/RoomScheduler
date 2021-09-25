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
import java.util.ArrayList;

public class RoomQueries {
    private static Connection connection;
    private static ArrayList<RoomEntry> roomsBySeats = new ArrayList<>();
    private static PreparedStatement addRoom;
    private static PreparedStatement getRoomsBySeats;
    private static ResultSet resultSet;
    
    public static void addRoom(String name, int seats){
        connection = DBConnection.getConnection();
        
        try{
            addRoom = connection.prepareStatement(
                    "insert into rooms (name, seats) values (?, ?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        } 
    }
    
    
    
    
    public static ArrayList<RoomEntry> getRoomsBySeats(){
        connection = DBConnection.getConnection();
        
        try{
            getRoomsBySeats = connection.prepareStatement(
                    "select * from rooms order by seats ASC");
            resultSet = getRoomsBySeats.executeQuery();
            
            while (resultSet.next()){
                RoomEntry aRoom = new RoomEntry(resultSet.getString(1), 
                        resultSet.getInt(2));
                roomsBySeats.add(aRoom);
            }
        }
        
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return roomsBySeats;
    }
    
}

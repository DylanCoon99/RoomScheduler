/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dylco
 */
import java.sql.Date;
import java.sql.Timestamp;

public class ReservationEntry {
    private final String faculty;
    private final String room;
    private final Date date;
    private final int seats;
    private final Timestamp timeStamp;
    
    public ReservationEntry(String faculty, String room, Date date, int seats, 
            Timestamp timeStamp){
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timeStamp = timeStamp;
    }
    
    public String getFaculty(){
        return this.faculty;
    }
    
    public String getRoom(){
        return this.room;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public Timestamp getTimeStamp(){
        return this.timeStamp;
    }
}

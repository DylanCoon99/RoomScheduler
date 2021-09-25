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

public class WaitlistEntry {
    private final String faculty;
    private final Date date;
    private final int seats;
    private final Timestamp timeStamp;
    
    public WaitlistEntry(String faculty, Date date, int seats, Timestamp timeStamp){
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timeStamp = timeStamp;
    }
    
    public String getFaculty(){
        return this.faculty;
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public int getSeats(){
        return this.seats;
    }
    
    public Timestamp getTimeStamp(){
        return this.timeStamp;
    }
}

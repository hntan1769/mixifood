package mixifood;

import java.sql.Time;

public class Diner {

    //declare variables
    private int Diner_id;
    private int Location_id;
    private String Diner_name;
    private String Address;
    private String Diner_phone;
    private Time Time_open;
    private Time Time_close;
    private Boolean Diner_status;

    //output
    @Override
    public String toString() {
        String str = "";
        str += Diner_id + " | " + Location_id + " | " + Diner_name + " | " + Address + " | " + Diner_phone + " | " + Diner_status;        
        return str;
    }
    
    //constructor    
    public Diner(int Diner_id, int Location_id, String Diner_name, String Address, String Diner_phone, Time Time_open, Time Time_close, Boolean Diner_status) {
        this.Diner_id = Diner_id;
        this.Location_id = Location_id;
        this.Diner_name = Diner_name;
        this.Address = Address;
        this.Diner_phone = Diner_phone;
        this.Time_open = Time_open;
        this.Time_close = Time_close;
        this.Diner_status = Diner_status;
    }
    
    public void DinerCopy(int Diner_id, int Location_id, String Diner_name, String Address, String Diner_phone, Time Time_open, Time Time_close, Boolean Diner_status) {
        this.Diner_id = Diner_id;
        this.Location_id = Location_id;
        this.Diner_name = Diner_name;
        this.Address = Address;
        this.Diner_phone = Diner_phone;
        this.Time_open = Time_open;
        this.Time_close = Time_close;
        this.Diner_status = Diner_status;
    }

    //getter setter
    public int getDiner_id() {
        return Diner_id;
    }

    public void setDiner_id(int Diner_id) {
        this.Diner_id = Diner_id;
    }

    public int getLocation_id() {
        return Location_id;
    }

    public void setLocation_id(int Location_id) {
        this.Location_id = Location_id;
    }

    public String getDiner_name() {
        return Diner_name;
    }

    public void setDiner_name(String Diner_name) {
        this.Diner_name = Diner_name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getDiner_phone() {
        return Diner_phone;
    }

    public void setDiner_phone(String Diner_phone) {
        this.Diner_phone = Diner_phone;
    }

    public Time getTime_open() {
        return Time_open;
    }

    public void setTime_open(Time Time_open) {
        this.Time_open = Time_open;
    }

    public Time getTime_close() {
        return Time_close;
    }

    public void setTime_close(Time Time_close) {
        this.Time_close = Time_close;
    }

    public Boolean getDiner_status() {
        return Diner_status;
    }

    public void setDiner_status(Boolean Diner_status) {
        this.Diner_status = Diner_status;
    }

}
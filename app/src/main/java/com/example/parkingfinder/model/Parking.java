package com.example.parkingfinder.model;

public class Parking {
    private String parkingId;
    private String capacity;
    private String remaining;
    private String available;

    public String getParkingId(){
        return parkingId;
    }

    public void setParkingId(String parkingId){
        this.parkingId = parkingId;
    }

    public String getCapacity(){
        return capacity;
    }
    public void setCapacity(String capacity){
        this.capacity = capacity;
    }

    public String getRemaining(){
        return remaining;
    }
    public void setRemaining(String remaining){
        this.remaining = remaining;
    }

    public String getAvailable(){
        return available;
    }
    public void setAvailable(String available){
        this.available = available;
    }
}

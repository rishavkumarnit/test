package com.rishav.parking;

import java.time.LocalDateTime;

public class VehicleSpace {
    private String spaceNumber;
    private boolean availability;
    private VehicleType vehicleType;
    private Vehicle vehicle;
    private LocalDateTime parkingTime;

    public VehicleSpace(String spaceNumber, VehicleType vehicleType){
        this.spaceNumber = spaceNumber;
        this.vehicleType = vehicleType;
    }

    public boolean getAvailability(){
        return this.availability;
    }

    public void setAvailability(boolean availability){
        this.availability = availability;
    }

    public void parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        this.setAvailability(false);
        this.parkingTime = LocalDateTime.now();
    }

    public void removeVehicle(){
        this.vehicle = null;
        this.setAvailability(true);
        this.parkingTime = null;
    }

    public LocalDateTime getParkingTime(){
        return this.parkingTime;
    }

    public String getSpaceNumber(){
        return this.spaceNumber;
    }

    public Vehicle getVehicle(){
        return this.vehicle;
    }

    public VehicleType getVehicleType(){
        return this.vehicleType;
    }
}

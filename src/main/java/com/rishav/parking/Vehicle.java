package com.rishav.parking;

public class Vehicle {
    private String registrationNo;
    private VehicleType vehicleType;
    private String color;

    public Vehicle(String registrationNo, VehicleType vehicleType, String color){
        this.registrationNo = registrationNo;
        this.vehicleType = vehicleType;
        this.color = color;
    }

    public String getRegistrationNo(){
        return this.registrationNo;
    }

    public VehicleType getVehicleType(){
        return this.vehicleType;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getRegistrationNo().equals(((Vehicle)obj).getRegistrationNo());
    }
}

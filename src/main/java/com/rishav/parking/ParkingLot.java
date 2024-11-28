package com.rishav.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int numberOfFloors;
    private int spacePerFloor;
    List<Floor> floors = new ArrayList<>();
    CostStrategy costStrategy;

    public ParkingLot(int numberOfFloors, int spacePerFloor, int maxCars, int maxBikes, int maxBusses, int maxTrucks){
        this.numberOfFloors = numberOfFloors;
        this.spacePerFloor = spacePerFloor;
        for(int i = 0; i < numberOfFloors; i++){
            floors.add(new Floor(i, spacePerFloor, maxCars, maxBikes, maxBusses, maxTrucks, costStrategy));
        }
    }

    public String addVehicle(String registrationNo, VehicleType vehicleType,  String color){
        Vehicle vehicle = new Vehicle(registrationNo, vehicleType, color);
        String spaceNumber = null;
        for(Floor floor : floors){
            spaceNumber = floor.addVehicle(vehicle);
            
        }
        if(spaceNumber == null){
            throw new RuntimeException( "No Space Available Right Now");
        }
        return spaceNumber;
    }

    public double removeVehicle(String registrationNo){
        for(Floor floor : floors){
            if(floor.isVehicleAvailable(registrationNo)){
                return floor.removeVehicle(registrationNo);
            }
        }
        return 0.0;
    }

    public void configureCostStrategy(double carPrice, double bikePrice, double busPrice, double truckPrice, Currency currency){
        this.costStrategy = new CostStrategy(carPrice,  bikePrice,  busPrice,  truckPrice, currency);
    }

    public String checkAvailability(int floorNumber){
        String availability = "";
        Floor floor = floors.get(floorNumber);
        availability += floor.checkAvailability();
        return availability;
    }

    public String displayStatus(){
        String availability = "";
        for(Floor floor : floors){
            availability += floor.checkAvailability();
        }
        return availability;
    }

}

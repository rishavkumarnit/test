package com.rishav.parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Floor {
    private int spacePerFloor;
    private int maxCars;
    private int maxBikes;
    private int maxBusses;
    private int maxTrucks; 
    private int floorNumber;
    private CostStrategy costStrategy;

    List<VehicleSpace> carSpace = new ArrayList<>();
    List<VehicleSpace> bikeSpace = new ArrayList<>();
    List<VehicleSpace> truckSpace = new ArrayList<>();
    List<VehicleSpace> busSpace = new ArrayList<>();

    Map<String, Vehicle> cars = new HashMap<>();
    Map<String, Vehicle> bikes = new HashMap<>();
    Map<String, Vehicle> trucks = new HashMap<>();
    Map<String, Vehicle> busses = new HashMap<>();
    
    public Floor(int floorNumber, int spacePerFloor, int maxCars, int maxBikes, int maxBusses, int maxTrucks, CostStrategy costStrategy){
        this.floorNumber = floorNumber;
        this.spacePerFloor = spacePerFloor;
        this.maxCars = maxCars;
        this.maxBikes = maxBikes;
        this.maxBusses = maxBusses;
        this.maxTrucks = maxTrucks;
        this.costStrategy = costStrategy;

        for(int i = 1; i <= maxCars; i++){
            carSpace.add(new VehicleSpace(i + " " + floorNumber + " " + "CAR", VehicleType.CAR));
        }

        for(int i = 1; i <= maxBikes; i++){
            bikeSpace.add(new VehicleSpace(i + " " + floorNumber + " " + "SPORTSCAR", VehicleType.BIKE));
        }

        for(int i = 1; i <= maxBusses; i++){
            busSpace.add(new VehicleSpace(i + " " + floorNumber + " " + "BUS", VehicleType.BUS));
        }

        for(int i = 1; i <= maxTrucks; i++){
            truckSpace.add(new VehicleSpace(i + " " + floorNumber + " " + "TRUCK", VehicleType.TRUCK));
        }
    }

    public String addVehicle(Vehicle vehicle){
        if(vehicle.getVehicleType() == VehicleType.CAR){
            if(carSpace.size() < maxCars){
                for(VehicleSpace vehicleSpace : carSpace){
                    if(vehicleSpace.getAvailability()){
                        vehicleSpace.parkVehicle(vehicle);
                        cars.put(vehicle.getRegistrationNo(), vehicle);
                        return vehicleSpace.getSpaceNumber();
                    }
                }
            }
        }
        else if(vehicle.getVehicleType() == VehicleType.BIKE){
            if(bikeSpace.size() < maxBikes){
                for(VehicleSpace vehicleSpace : bikeSpace){
                    if(vehicleSpace.getAvailability()){
                        vehicleSpace.parkVehicle(vehicle);
                        bikes.put(vehicle.getRegistrationNo(), vehicle);
                        return vehicleSpace.getSpaceNumber();
                    }
                }
            }
        }
        else if(vehicle.getVehicleType() == VehicleType.BUS){
            if(busSpace.size() < maxBusses){
                for(VehicleSpace vehicleSpace : busSpace){
                    if(vehicleSpace.getAvailability()){
                        vehicleSpace.parkVehicle(vehicle);
                        busses.put(vehicle.getRegistrationNo(), vehicle);
                        return vehicleSpace.getSpaceNumber();
                    }
                }
            }
        }
        else if(vehicle.getVehicleType() == VehicleType.TRUCK){
            if(truckSpace.size() < maxTrucks){
                for(VehicleSpace vehicleSpace : truckSpace){
                    if(vehicleSpace.getAvailability()){
                        vehicleSpace.parkVehicle(vehicle);
                        trucks.put(vehicle.getRegistrationNo(), vehicle);
                        return vehicleSpace.getSpaceNumber();
                    }
                }
            }
        }
        return null;
    }


    public double removeVehicle(String registrationNumber){
        Vehicle vehicle = null;
        if(cars.containsKey(registrationNumber)){
            vehicle = cars.get(registrationNumber);
        }
        else if(bikes.containsKey(registrationNumber)){
            vehicle = bikes.get(registrationNumber);
        }
        else if(busses.containsKey(registrationNumber)){
            vehicle = busses.get(registrationNumber);
        }
        else{
            vehicle = trucks.get(registrationNumber);
        }
        if(vehicle == null){
            return 0.0;
        }
        if(vehicle.getVehicleType() == VehicleType.CAR){
            for(VehicleSpace vehicleSpace : carSpace){
                if(vehicleSpace.getVehicle().equals(vehicle)){
                    double fair = costStrategy.calculateFair(vehicleSpace);
                    vehicleSpace.removeVehicle();
                    cars.remove(vehicle.getRegistrationNo());
                    return fair;
                }
            }

        }
        else if(vehicle.getVehicleType() == VehicleType.BIKE){
            for(VehicleSpace vehicleSpace : bikeSpace){
                if(vehicleSpace.getAvailability()){
                    double fair = costStrategy.calculateFair(vehicleSpace);
                    vehicleSpace.removeVehicle();
                    bikes.remove(vehicle.getRegistrationNo());
                    return fair;
                }
            }
        }
        else if(vehicle.getVehicleType() == VehicleType.BUS){
            for(VehicleSpace vehicleSpace : busSpace){
                if(vehicleSpace.getAvailability()){
                    double fair = costStrategy.calculateFair(vehicleSpace);
                    vehicleSpace.removeVehicle();
                    busses.remove(vehicle.getRegistrationNo());
                    return fair;
                }
            }

        }
        else if(vehicle.getVehicleType() == VehicleType.TRUCK){
            for(VehicleSpace vehicleSpace : truckSpace){
                if(vehicleSpace.getAvailability()){
                    double fair = costStrategy.calculateFair(vehicleSpace);
                    vehicleSpace.removeVehicle();
                    trucks.remove(vehicle.getRegistrationNo());
                    return fair;
                }
            }
        }

        return 0.0;
    }

    public boolean isVehicleAvailable(String registrationNumber){
        if(cars.containsKey(registrationNumber)){
            return true;
        }
        else if(bikes.containsKey(registrationNumber)){
            return true;
        }
        else if(busses.containsKey(registrationNumber)){
            return true;
        }
        else if(trucks.containsKey(registrationNumber)){
            return true;
        }
        return false;
    }

    public String checkAvailability(){
        String availability = "Availability for " + floorNumber + " ";
        availability += "Cars " + (maxCars - cars.size()) + " ";
        availability += "Bikes" + (maxBikes - bikes.size()) + " ";
        availability += "Busses" + (maxBusses - busses.size()) + " ";
        availability += "Trucks" + (maxTrucks - trucks.size()) + " ";
        return availability;
    }

}

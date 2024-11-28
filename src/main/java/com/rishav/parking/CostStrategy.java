package com.rishav.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class CostStrategy {

    private double carPrice;
    private double bikePrice;
    private double busPrice;
    private double truckPrice;
    private Currency currency;

    public CostStrategy(double carPrice,  double bikePrice, double busPrice, double truckPrice, Currency currency){
        this.currency = currency;
        this.carPrice = carPrice;
        this.bikePrice = bikePrice;
        this.busPrice = busPrice;
        this.truckPrice = truckPrice;
    }

    public double calculateFair(VehicleSpace vehicleSpace) {
        if(vehicleSpace.getVehicleType() == VehicleType.CAR){
            Duration duration = Duration.between(vehicleSpace.getParkingTime(), LocalDateTime.now());
            long hours = duration.toHours();
            return hours * carPrice * currency.getValue();
        }
        else if(vehicleSpace.getVehicleType() == VehicleType.BIKE){
            Duration duration = Duration.between(vehicleSpace.getParkingTime(), LocalDateTime.now());
            long hours = duration.toHours();
            return hours * bikePrice * currency.getValue();
        }
        else if(vehicleSpace.getVehicleType() == VehicleType.TRUCK){
            Duration duration = Duration.between(vehicleSpace.getParkingTime(), LocalDateTime.now());
            long hours = duration.toHours();
            return hours * busPrice * currency.getValue();
        }
        else if(vehicleSpace.getVehicleType() == VehicleType.BUS){
            Duration duration = Duration.between(vehicleSpace.getParkingTime(), LocalDateTime.now());
            long hours = duration.toHours();
            return hours * truckPrice * currency.getValue();
        }

        return 0.0;
    }
    
}

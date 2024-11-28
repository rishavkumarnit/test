package com.rishav.parking;


public class ParkingApplication {

	public static void main(String[] args) {
		ParkingLot parkingLot = new ParkingLot(3, 8, 2, 2, 2, 2);
		String token = parkingLot.addVehicle("abcd", VehicleType.BIKE, "black");
		System.out.println(token);
	}

}

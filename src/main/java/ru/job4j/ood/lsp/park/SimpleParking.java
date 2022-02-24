package ru.job4j.ood.lsp.park;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {

    private int truckSlots;
    private int passengerCarSlots;
    private final List<Vehicle> vehicles = new ArrayList<>();

    public SimpleParking(int truckSlots, int passengerCarSlots) {
        this.truckSlots = truckSlots;
        this.passengerCarSlots = passengerCarSlots;
    }

    @Override
    public boolean park(Vehicle vehicle) {
        boolean result = false;
        if (vehicle.getSize() == PassengerCar.SIZE && passengerCarSlots > 0) {
            vehicles.add(vehicle);
            passengerCarSlots--;
            result = true;
        } else if (vehicle.getSize() > PassengerCar.SIZE && truckSlots > 0) {
            vehicles.add(vehicle);
            truckSlots--;
            result = true;
        } else if (vehicle.getSize() > PassengerCar.SIZE && truckSlots < 1
                && vehicle.getSize() <= passengerCarSlots) {
            vehicles.add(vehicle);
            passengerCarSlots -= vehicle.getSize();
            result = true;
        }
        return result;
    }

    @Override
    public int getTruckSlots() {
        return truckSlots;
    }

    @Override
    public int getPassengerCarSlots() {
        return passengerCarSlots;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return List.copyOf(vehicles);
    }
}

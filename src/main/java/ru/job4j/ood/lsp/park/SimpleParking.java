package ru.job4j.ood.lsp.park;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {

    private final int truckSlots;
    private final int passengerCarSlots;
    private final List<Vehicle> vehicles = new ArrayList<>();

    public SimpleParking(int truckSlots, int passengerCarSlots) {
        this.truckSlots = truckSlots;
        this.passengerCarSlots = passengerCarSlots;
    }

    @Override
    public boolean park(Vehicle car) {
        return false;
    }

    @Override
    public int getTruckSlots() {
        return 0;
    }

    @Override
    public int getPassengerCarSlots() {
        return 0;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return null;
    }
}

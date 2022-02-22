package ru.job4j.ood.lsp.park;

import java.util.List;

public interface Parking {

    boolean park(Vehicle car);

    int getTruckSlots();

    int getPassengerCarSlots();

    List<Vehicle> getVehicles();
}

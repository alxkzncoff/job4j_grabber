package ru.job4j.ood.lsp.park;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleParkingTest {

    @Test @Ignore
    public void whenParkTrue() {
        Parking simpleParking = new SimpleParking(1, 2);
        Vehicle passengerCar = new PassengerCar();
        assertTrue(simpleParking.park(passengerCar));
    }

    @Test @Ignore
    public void whenPass() {
        Parking simpleParking = new SimpleParking(1, 2);
        Vehicle passengerCar1 = new PassengerCar();
        Vehicle passengerCar2 = new PassengerCar();
        Vehicle truck = new Truck(2);
        simpleParking.park(passengerCar1);
        simpleParking.park(passengerCar2);
        simpleParking.park(truck);
        List<Vehicle> expected = new ArrayList<>(List.of(passengerCar1, passengerCar2, truck));
        assertEquals(expected, simpleParking.getVehicles());
    }

    @Test @Ignore
    public void whenPassTwoTrucks() {
        Parking simpleParking = new SimpleParking(1, 2);
        Vehicle truck1 = new Truck(4);
        Vehicle truck2 = new Truck(2);
        simpleParking.park(truck1);
        simpleParking.park(truck2);
        List<Vehicle> expected = new ArrayList<>(List.of(truck1, truck2));
        assertEquals(expected, simpleParking.getVehicles());
    }

    @Test @Ignore
    public void whenFailSecondTruck() {
        Parking simpleParking = new SimpleParking(1, 2);
        Vehicle passengerCar1 = new PassengerCar();
        Vehicle passengerCar2 = new PassengerCar();
        Vehicle truck1 = new Truck(2);
        Vehicle truck2 = new Truck(3);
        simpleParking.park(passengerCar1);
        simpleParking.park(passengerCar2);
        simpleParking.park(truck1);
        assertFalse(simpleParking.park(truck2));
    }

    @Test @Ignore
    public void whenFailTwoPassengerCars() {
        Parking simpleParking = new SimpleParking(1, 1);
        Vehicle passengerCar1 = new PassengerCar();
        Vehicle passengerCar2 = new PassengerCar();
        Vehicle truck1 = new Truck(2);
        simpleParking.park(passengerCar1);
        simpleParking.park(truck1);
        assertFalse(simpleParking.park(passengerCar2));
    }
}
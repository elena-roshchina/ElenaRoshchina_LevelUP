package ru.levelup.elena.roshchina.qa.homework_02.aircrafts;

import ru.levelup.elena.roshchina.qa.homework_02.aircrafts.Aircraft;

public class AirShip extends Aircraft {
    public AirShip(String model, int seatingCapacity, int carryingCapacity, double rangeOfFlight) {
        this.model = model;
        this.aircraftType = "airship";
        this.seatingCapacity = seatingCapacity;
        this.carryingCapacity = carryingCapacity;
        this.rangeOfFlight = rangeOfFlight;
    }
}

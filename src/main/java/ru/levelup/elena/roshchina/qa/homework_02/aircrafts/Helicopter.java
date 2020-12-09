package ru.levelup.elena.roshchina.qa.homework_02.aircrafts;

import ru.levelup.elena.roshchina.qa.homework_02.aircrafts.Aircraft;

public class Helicopter extends Aircraft {

    public Helicopter(String model, int seatingCapacity, int carryingCapacity, double rangeOfFlight) {
        this.model = model;
        this.aircraftType = "helicopter";
        this.seatingCapacity = seatingCapacity;
        this.carryingCapacity = carryingCapacity;
        this.rangeOfFlight = rangeOfFlight;
    }

}

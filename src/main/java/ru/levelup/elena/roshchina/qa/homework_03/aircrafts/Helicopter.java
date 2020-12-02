package ru.levelup.elena.roshchina.qa.homework_03.aircrafts;

import ru.levelup.elena.roshchina.qa.homework_03.aircrafts.Aircraft;

public class Helicopter extends Aircraft {

    public Helicopter(String model, int seatingCapacity, int carryingCapacity, double rangeOfFlight) {
        super(model, seatingCapacity, carryingCapacity, rangeOfFlight);
        this.aircraftType = "helicopter";
    }
}

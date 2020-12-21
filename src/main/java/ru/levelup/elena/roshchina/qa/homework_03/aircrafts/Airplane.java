package ru.levelup.elena.roshchina.qa.homework_03.aircrafts;


public class Airplane extends Aircraft {
    public Airplane(String model, int seatingCapacity, int carryingCapacity, double rangeOfFlight) {
        super(model, seatingCapacity, carryingCapacity, rangeOfFlight);
        this.aircraftType = "airplane";

    }
}


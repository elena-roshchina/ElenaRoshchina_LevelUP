package ru.levelup.elena.roshchina.qa.homework_03.aircrafts;


public class AirShip extends Aircraft {

    public AirShip(String model, int seatingCapacity, int carryingCapacity, double rangeOfFlight) {
        super(model, seatingCapacity, carryingCapacity, rangeOfFlight);
        this.aircraftType = "airship";
    }
}

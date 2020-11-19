package ru.levelup.elena.roshchina.qa.homework_02;

public abstract class Aircraft {
    protected Boolean hasEngine;
    protected String model;
    protected String aircraftType;
    protected int seatingCapacity;
    protected int carryingCapacity;
    protected int rangeOfFlight;
    protected float specificFuelConsumption;

    public Aircraft(Boolean hasEngine, String model, String aircraftType,
                    int seatingCapacity, int carryingCapacity,
                    int rangeOfFlight, float specificFuelConsumption) {
        this.hasEngine = hasEngine;
        this.model = model;
        this.aircraftType = aircraftType;
        this.seatingCapacity = seatingCapacity;
        this.carryingCapacity = carryingCapacity;
        this.rangeOfFlight = rangeOfFlight;
        this.specificFuelConsumption = specificFuelConsumption;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "hasEngine=" + hasEngine +
                ", model='" + model + '\'' +
                ", aircraftType='" + aircraftType + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                ", carryingCapacity=" + carryingCapacity +
                ", rangeOfFlight=" + rangeOfFlight +
                ", specificFuelConsumption=" + specificFuelConsumption +
                '}';
    }

    public Boolean getHasEngine() {
        return hasEngine;
    }

    public String getModel() {
        return model;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public int getRangeOfFlight() {
        return rangeOfFlight;
    }

    public float getSpecificFuelConsumption() {
        return specificFuelConsumption;
    }
}

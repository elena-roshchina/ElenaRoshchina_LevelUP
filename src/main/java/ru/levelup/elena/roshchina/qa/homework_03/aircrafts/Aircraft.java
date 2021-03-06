package ru.levelup.elena.roshchina.qa.homework_03.aircrafts;

import ru.levelup.elena.roshchina.qa.homework_03.aviaexceptions.IllegalAircraftSpecificationException;

public abstract class Aircraft implements Comparable {
    protected String model = "";
    protected String uid = "";
    protected String aircraftType = "aircraft";
    protected int seatingCapacity = 0;   // passengers
    protected int carryingCapacity = 0;  // tonne
    protected double rangeOfFlight = 0.0; // 1000 km

    public Aircraft(String model, int seatingCapacity, int carryingCapacity, double rangeOfFlight) {
        if (seatingCapacity < 0 || carryingCapacity < 0 || rangeOfFlight < 0.0 ){
            String errorMessage = "\n\tAirplane() error: Aircraft is not created, wrong specifications:\n";
            if (seatingCapacity < 0) errorMessage += "\t\tseatingCapacity = " + Integer.toString(seatingCapacity) + ",\n";
            if (carryingCapacity < 0) errorMessage += "\t\tcarryingCapacity = " + Integer.toString(carryingCapacity) + ",\n";
            if (rangeOfFlight < 0) errorMessage += "\t\trangeOfFlight = " + Double.toString(rangeOfFlight)+ "\n";
            throw new IllegalAircraftSpecificationException("Illegal aircraft specification" + errorMessage);
        } else {
            this.model = model;
            this.seatingCapacity = seatingCapacity;
            this.carryingCapacity = carryingCapacity;
            this.rangeOfFlight = rangeOfFlight;
        }
    }

    public int compareTo(Object o) {
        return Double.compare(this.rangeOfFlight, ((Aircraft) o).rangeOfFlight);
    }

    @Override
    public String toString() {
        return  aircraftType + " " + model + ", reg.number=" + uid + "\n" +
                "\t seatingCapacity=" + seatingCapacity + "\n" +
                "\t carryingCapacity=" + carryingCapacity + "\n" +
                "\t rangeOfFlight=" + rangeOfFlight + "\n ---------------------------" ;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public double getRangeOfFlight() {
        return rangeOfFlight;
    }

}

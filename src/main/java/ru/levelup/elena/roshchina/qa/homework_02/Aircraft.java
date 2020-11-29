package ru.levelup.elena.roshchina.qa.homework_02;

public abstract class Aircraft implements Comparable {
    protected String model = "";
    protected String uid = "";
    protected String aircraftType = "aircraft";
    protected int seatingCapacity = 0;   // passengers
    protected int carryingCapacity = 0;  // tonne
    protected double rangeOfFlight = 0.0; // 1000 km


    public Aircraft() {
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

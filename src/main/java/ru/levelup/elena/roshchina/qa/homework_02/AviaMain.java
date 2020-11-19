package ru.levelup.elena.roshchina.qa.homework_02;

public class AviaMain {
    public static void main(String[] args){
    Airplane airplane = new Airplane(true, "Boing 737-100",
            "airplane", 103, 600,
            1000, 5.0f);
        System.out.println(airplane.toString());
    }

}

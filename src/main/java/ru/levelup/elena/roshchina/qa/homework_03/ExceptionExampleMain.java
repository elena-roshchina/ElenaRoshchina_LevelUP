package ru.levelup.elena.roshchina.qa.homework_03;

import ru.levelup.elena.roshchina.qa.homework_03.aircrafts.Aircraft;
import ru.levelup.elena.roshchina.qa.homework_03.aircrafts.Airplane;
import ru.levelup.elena.roshchina.qa.homework_03.aircrafts.AirShip;
import ru.levelup.elena.roshchina.qa.homework_03.aviaexceptions.DepotSizeException;
import ru.levelup.elena.roshchina.qa.homework_03.aviaexceptions.IllegalAircraftSpecificationException;
import ru.levelup.elena.roshchina.qa.homework_03.aviaexceptions.ModelNotSpecifiedException;

import java.util.ArrayList;

public class ExceptionExampleMain {

    public static void main(String[] args) {
        Company aviaCompany = new Company("Flying Fox INC.");
        // Aircraft creation error
        System.out.println("\nCase 1. Try to add new airplane with negative integer specification:\n");
        try {
            aviaCompany.addAirCraft(new Airplane("Antigrav",0,
                    -30, 12.0));
        } catch (ModelNotSpecifiedException e) {
            e.printStackTrace();
        } catch (IllegalAircraftSpecificationException e) {
            e.printStackTrace();
        }
        System.out.println("\nCase 2. Try to add new airplane with empty model name: \n");
        // Adding new aircraft: Model name error
        try {
            aviaCompany.addAirCraft(new Airplane("",14,
                    23, 12.0));
        } catch (ModelNotSpecifiedException e) {
            e.printStackTrace();
        } catch (IllegalAircraftSpecificationException e) {
            e.printStackTrace();
        }

        System.out.println("\nCase 3. Try get statistics from empty array: \n");
        String stat = "";
        try{
            stat = aviaCompany.getCompanyStat();
            System.out.println(stat);
        } catch (DepotSizeException e){
            e.printStackTrace();
        }

        System.out.println("\nCase 4. Search in empty aircraft list: \n");
        try{
            ArrayList<Aircraft> airplanesFound = aviaCompany.findAirCraftByParameters(100, 400,
                    25, 500,
                    1.5, 5.5);
            System.out.println("Results:\n");
            for (Aircraft a: airplanesFound) {
                System.out.println(a);
            }
        } catch (DepotSizeException e){
            e.printStackTrace();
        }

        System.out.println("Let add some valid aircraft to the our company's depot:");
        try {
            aviaCompany.addAirCraft(new Airplane("AAA",14,
                    23, 12.0));
        } catch (ModelNotSpecifiedException e) {
            e.printStackTrace();
        } catch (IllegalAircraftSpecificationException e) {
            e.printStackTrace();
        }
        System.out.println("\nCase 5. Search with wrong conditions: \n");

        try{
            ArrayList<Aircraft> airplanesFound = aviaCompany.findAirCraftByParameters(-3, -4,
                    -1000, -1,
                    -1.5, -5.5);
            System.out.println("Results:\n");
            for (Aircraft a: airplanesFound) {
                System.out.println(a);
            }
        } catch (DepotSizeException e){
            e.printStackTrace();
        }
    }
}

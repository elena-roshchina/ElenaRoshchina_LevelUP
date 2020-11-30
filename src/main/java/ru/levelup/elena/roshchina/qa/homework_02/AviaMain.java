/* Homework_02
 * 3.	Авиакомпания. Определить иерархию самолетов, вертолетов, квадракоптеров и т.д. Создать авиакомпанию.
 * Посчитать общую вместимость и грузоподъемность.
 * Провести сортировку летных средств компании по дальности полета.
 * Найти самолет в компании, соответствующий заданному диапазону параметров.
 */
package ru.levelup.elena.roshchina.qa.homework_02;

import ru.levelup.elena.roshchina.qa.homework_02.aircrafts.AirShip;
import ru.levelup.elena.roshchina.qa.homework_02.aircrafts.Aircraft;
import ru.levelup.elena.roshchina.qa.homework_02.aircrafts.Airplane;
import ru.levelup.elena.roshchina.qa.homework_02.aircrafts.Helicopter;

import java.util.ArrayList;

public class AviaMain {

    public static void main(String[] args){
        Company aviaCompany = new Company("Flying Fox INC.");
        aviaCompany.addAirCraft(new AirShip("LZ 127",60,
                23, 12.0));
        aviaCompany.addAirCraft(new Helicopter("Sycorsky S92",26,
                2, 0.45));
        aviaCompany.addAirCraft(new Airplane("TY157",100,
                30, 2.0));
        aviaCompany.addAirCraft(new Airplane("Boeing 737",150,
                50, 4.0));
        aviaCompany.addAirCraft(new Airplane("Cessna",2,
                0, 0.5));
        aviaCompany.addAirCraft(new Airplane("Il86",120,
                34, 8.0));
        aviaCompany.addAirCraft(new Airplane("Concord",300,
                21, 10.0));
        aviaCompany.addAirCraft(new Airplane("Antey",5,
                60, 5.0));

        System.out.println(aviaCompany.getCompanyInfo());
        ArrayList<Aircraft> airplanesFound = aviaCompany.findAirCraftByParameters(100, 400,
                25, 500,
                1.5, 5.5);
        System.out.println("Results:\n");
        for (Aircraft a: airplanesFound) {
            System.out.println(a);
        }
        System.out.println();

        ArrayList<Aircraft> airplanesSorted = aviaCompany.getSortedAircrafts(true);
        System.out.println("Aircrafts list (sorted by range of flight):");
        System.out.println();
        for (Aircraft a: airplanesSorted) {
            System.out.println(a);
        }
    }




}

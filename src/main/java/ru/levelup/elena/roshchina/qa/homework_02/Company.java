/**
 * Класс авиакомпании со свойствами <b>title</b>, <b>address</b>, <b>owner</b>, <b>aircrafts</b>.
 * Методы:
 * @autor Elena
 * @version 1.0
 */

package ru.levelup.elena.roshchina.qa.homework_02;

import ru.levelup.elena.roshchina.qa.homework_02.aircrafts.Aircraft;
import ru.levelup.elena.roshchina.qa.homework_02.autority.AircraftRegistration;
import ru.levelup.elena.roshchina.qa.homework_02.autority.AviationAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Company implements AircraftRegistration {
    /** Поле название */
    private String title;
    /** Поле адресс */
    private String address;
    /** Поле владелец */
    private String owner;
    /** Поле массив воздушнотранспортных средств авиакомпании, ключ - регистрационный номер */
    private HashMap<String, Aircraft> aircrafts = new HashMap<String, Aircraft>();
    /**
     * Конструктор - создание нового объекта с названием
     */
    public Company(String title) {
        this.title = title;
        System.out.println("New aviacompany " + title + " was established\n");

    }
    /**
     * Конструктор - создание нового объекта с названием, адресом
     */
    public Company(String title, String address) {
        this.title = title;
        this.address = address;
        System.out.println("New aviacompany " + title + " was established\n");
    }
    /**
     * Конструктор - создание нового объекта с названием, адресом и владельцем
     */
    public Company(String title, String address, String owner) {
        this.title = title;
        this.address = address;
        this.owner = owner;
        System.out.println("New aviacompany " + title + " was established\n");
    }
    /**
     * Функция получения информации о компании
     * @return возвращает строку.
     */
    public String getCompanyInfo() {
        String line = "--------------------------\nCompany title: " + this.title;
        if (this.address != null) line += ",\n Address: " + this.address;
        if (this.owner != null) line += ",\n Owner: " + this.owner;
        line += "\nThe aircraft depot has " + Integer.toString(aircrafts.size()) + " units";
        line += "\nTotal Carrying Capacity: " + Integer.toString(getTotalCarryingCapacity())+ ", tonnes";
        line += "\nTotal Seating Capacity: " + Integer.toString(getTotalSeatingCapacity()) + ", people";
        line +="\n--------------------------";
        return line;
    }
    /**
     * Функция добавления объекта классов-наследников класса Aircraft с
     * присвоением регистрационного номера в едином реестре если объект новый
     * @see AviationAuthority#getRegNumber()
     */
    public void addAirCraft(Aircraft a) {
        String uid;
        if ("".equals(a.getUid())) {
            uid = Integer.toString(av.getRegNumber());
            System.out.println("New " +a.getAircraftType() + " " + a.getModel() + " was registered, number "  + uid);
            a.setUid(uid);
        } else {
            uid = a.getUid();
        }
        aircrafts.put(uid, a);
    }
    /**
     * Функция поиска воздушного средства по его регистрационному номеру
     * @return возвращает объект Aircraft
     */
    public Aircraft findAirCraftByUIN(String uid) {
        return aircrafts.get(uid);
    }
    /**
     * Функция определения диапазона
     * @return список из двух int
     */
    private int[] swapTwoValues(int min, int max) {
        int buffer;
        if (max < min) {
            buffer = max;
            max = min;
            min = buffer;
        }
        return new int[]{min, max};
    }

    /**
     * Функция определения диапазона
     * @return список из двух double
     */
    private double[] swapTwoValues(double min, double max) {
        double buffer;
        if (max < min) {
            buffer = max;
            max = min;
            min = buffer;
        }
        return new double[]{min, max};
    }
    /**
     * Функция получения общей вместимости
     * @return целое число
     */
    public int getTotalSeatingCapacity(){
        ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>(aircrafts.values());
        int total = 0;
        for (Aircraft a: airplanes) {
            total += a.getSeatingCapacity();
        }
        return total;
    }
    /**
     * Функция получения общей грузоподъемности
     * @return целое число
     */
    public int getTotalCarryingCapacity(){
        ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>(aircrafts.values());
        int total = 0;
        for (Aircraft a: airplanes) {
            total += a.getCarryingCapacity();
        }
        return total;
    }

    /**
     * Функция сортировки воздушных средств по дальности полета
     * принимает логический параметр, определяющий порядок сортировки
     * @return список воздушных средств ArrayList<Aircraft>
     */
    public ArrayList<Aircraft> getSortedAircrafts(Boolean descent) {
        ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>(aircrafts.values());
        Aircraft buffer;
        for (int i = 0; i < airplanes.size(); i++) {
            for (int j = i + 1; j < airplanes.size(); j++) {
                if (descent) {
                    if (airplanes.get(i).compareTo(airplanes.get(j)) > 0) {
                        buffer = airplanes.get(i);
                        airplanes.set(i, airplanes.get(j));
                        airplanes.set(j, buffer);
                    }
                } else {
                    if (airplanes.get(i).compareTo(airplanes.get(j)) < 0) {
                        buffer = airplanes.get(i);
                        airplanes.set(i, airplanes.get(j));
                        airplanes.set(j, buffer);
                    }
                }
            }
        }
        return airplanes;
    }
    /**
     * Функция поиска воздушного средства в диапазонах параметров
     * принимает минимальной и максимальное значение вместимости, грузоподъемности и дальности полета
     * @return список воздушных средств ArrayList<Aircraft>
     */
    public ArrayList<Aircraft> findAirCraftByParameters(int seatingCapacityMin, int seatingCapacityMax,
                                             int carryingCapacityMin, int carryingCapacityMax,
                                             double rangeOfFlightMin, double rangeOfFlightMax) {
        int[] seats = null, load = null;
        double[] range = null;
        String message = "Search by parameters: \n";
        if (seatingCapacityMin>0 & seatingCapacityMax>0){
            seats = swapTwoValues(seatingCapacityMin, seatingCapacityMax);
            message += "\tseating capacity range(" + Integer.toString(seats[0]) + ", " + Integer.toString(seats[1]) + "),\n";
        }

        if(carryingCapacityMin>0 & carryingCapacityMax>0){
            load = swapTwoValues(carryingCapacityMin, carryingCapacityMax);
            message += "\tcarrying capacity range(" + Integer.toString(load[0]) + ", " + Integer.toString(load[1]) + "),\n";
        }

        if (rangeOfFlightMin>0.0 & rangeOfFlightMax>0.0) {
            range = swapTwoValues(rangeOfFlightMin, rangeOfFlightMax);
            message += "\trange Of Flight(" + Double.toString(range[0]) + ", " + Double.toString(range[1]) + ")\n";
        }
        System.out.println(message);
        System.out.println();
        ArrayList<Aircraft> airplanes = new ArrayList<Aircraft>(aircrafts.values());
        Iterator<Aircraft> it = airplanes.iterator();
        while (it.hasNext()){
            Aircraft a = it.next();
            if (seats != null & (a.getSeatingCapacity() < seats[0] | seats[1] < a.getSeatingCapacity())) {
                it.remove();
                continue;
            }
            if (load != null & (a.getCarryingCapacity() < load[0] | load[1] < a.getCarryingCapacity())) {
                it.remove();
                continue;
            }
            if (range != null & (a.getRangeOfFlight() < range[0] | range[1] < a.getRangeOfFlight())) {
                it.remove();
            }
        }
        return airplanes;
    }
}
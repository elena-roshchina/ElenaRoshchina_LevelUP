package ru.levelup.elena.roshchina.qa.utils;

public class GenPassword {

    public static String getPassword(String text){
        return text + Integer.toString((int)Math.floor(Math.PI*10000));
    }
}

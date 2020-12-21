package ru.levelup.elena.roshchina.qa.homework_03.autority;

public class AviationAuthority {
    private int index;
    private static AviationAuthority aviationAuthority = new AviationAuthority();
    private AviationAuthority(){
    }
    public static AviationAuthority getAviationAuthority(){
        return aviationAuthority;
    }
    public int getRegNumber(){
        return index++;
    }
}

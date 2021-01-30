package ru.levelup.elena.roshchina.qa.homework_05;

public class TestUser {

    private String URL = "https://mail.ru/";
    private String BOX = "vasyaignatev85";
    private String KEY = "ufybvtl$";
    private String SERVICE = "mail.ru";
    private String ACCOUNT_TITLE_FRAGMENT = "Входящие";
    public TestUser() {
    }

    public String getUrl() {
        return URL;
    }

    public String getBox() {
        return BOX;
    }

    public String getKey() {
        return KEY;
    }

    public String getEmail() {
        return this.getBox() + "@" + this.SERVICE;
    }

    public String getAccountTitleFragment() {
        return this.ACCOUNT_TITLE_FRAGMENT;
    }
}

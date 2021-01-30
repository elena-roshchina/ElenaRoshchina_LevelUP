package ru.levelup.elena.roshchina.qa.homework_06;

public class TestUser {

    private String BOX = "vasyaignatev85";
    private String KEY = "ufybvtl$";
    private String SERVICE = "mail.ru";
    private String ACCOUNT_TITLE_FRAGMENT = "Входящие";
    private String USERNAME = "Василий Игнатьев";
    public TestUser() {
    }

    public String getBox() {
        return BOX;
    }
    public String getUserName() {
        return USERNAME;
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

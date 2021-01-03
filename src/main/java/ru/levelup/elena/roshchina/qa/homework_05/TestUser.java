package ru.levelup.elena.roshchina.qa.homework_05;

public class TestUser {

    private String url = "https://mail.ru/";
    private String box = "vasyaignatev85";
    private String key = "ufybvtl$";
    private String service = "mail.ru";
    private String accountTitleFragment = "Входящие";
    public TestUser() {
    }

    public String getUrl() {
        return url;
    }

    public String getBox() {
        return box;
    }

    public String getKey() {
        return key;
    }

    public String getEmail() {
        return this.getBox() + "@" + this.service;
    }

    public String getAccountTitleFragment() {
        return this.accountTitleFragment;
    }
}

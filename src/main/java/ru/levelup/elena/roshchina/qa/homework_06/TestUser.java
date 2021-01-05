package ru.levelup.elena.roshchina.qa.homework_06;

public class TestUser {

    private String box = "vasyaignatev85";
    private String key = "ufybvtl$";
    private String service = "mail.ru";
    private String accountTitleFragment = "Входящие";
    private String username = "Василий Игнатьев";
    public TestUser() {
    }

    public String getBox() {
        return box;
    }
    public String getUserName() {
        return username;
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

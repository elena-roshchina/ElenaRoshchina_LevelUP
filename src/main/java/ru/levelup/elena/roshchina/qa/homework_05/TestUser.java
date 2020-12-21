package ru.levelup.elena.roshchina.qa.homework_05;

public class TestUser {
    
    private String url;
    private String box;
    private String key;

    public TestUser(String postService) {
        if (postService.equals("mail.ru")){
            this.url = "https://mail.ru/";
            this.box = "vasyaignatev85";
            this.key = "ufybvtl$";
        } else {
            System.out.println("No service defined");
        }
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
}

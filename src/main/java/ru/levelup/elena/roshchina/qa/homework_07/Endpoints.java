package ru.levelup.elena.roshchina.qa.homework_07;

public class Endpoints {
    public static final String BASEURL = "https://gorest.co.in/public-api";
    public static final String USERSENDP = "/users";
    public static final String POSTSENDP = "/posts";
    public static final String COMMENTSENDP = "/comments";

    public static String getUserFilterUrl(String name, String email){
        return BASEURL + USERSENDP + "?name=" + name + "&email=" + email;
    };
    public static String getPostFilterUrl(String title){
        return BASEURL + POSTSENDP + "?title=" + title;
    };

    public static String getCommentFilterUrl(String name, String postID, String email){
        return BASEURL + COMMENTSENDP + "?post_id=" +  postID + "&name=" +  name + "&email=" + email;
    };

}

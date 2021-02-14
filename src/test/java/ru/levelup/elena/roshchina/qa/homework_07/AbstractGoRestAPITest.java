package ru.levelup.elena.roshchina.qa.homework_07;

import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.levelup.elena.roshchina.qa.homework_07.Endpoints.*;

public abstract class AbstractGoRestAPITest extends Config{
    protected ArrayList<Map<String, String>> userData;
    protected ArrayList<Map<String, String>> postData;
    protected ArrayList<Map<String, String>> commentData;

    protected int getUserId(String name, String email){
        Users users = given()
                .when()
                .queryParam("name", name).queryParam("email", email)
                .get(BASEURL + USERSENDP)
                .then()
                .extract()
                .body()
                .as(Users.class);
        List<SingleUser> usersData = users.getData();
        if (usersData != null && usersData.size() >0)
            return usersData.get(0).getId();
        else
            return -1;
    }

    protected int getPostId(String title){
        Posts posts = given()
                .when()
                .param("title", title)
                .get(BASEURL + POSTSENDP)
                .then()
                .extract()
                .body()
                .as(Posts.class);
        List<SinglePost> postsData = posts.getData();
        if (postsData != null && postsData.size() >0)
            return postsData.get(0).getId();
        else
            return -1;
    }

    protected int getCommentId(String name, String postID, String email){
        Comments commentsPojo = given()
                .when()
                .queryParam("name", name).queryParam("post_id", postID).queryParam("email", email)
                .get(BASEURL + COMMENTSENDP)
                .then()
                .extract()
                .body()
                .as(Comments.class);
        List<SingleComment> comments = commentsPojo.getData();
        if (comments != null && comments.size() >0)
            return comments.get(0).getId();
        else
            return -1;
    }

    @BeforeMethod
    public void setUp() throws IOException {
        userData = new ArrayList<>();
        postData = new ArrayList<>();
        commentData = new ArrayList<>();

        String[] data;
        String line;
        File file;
        FileReader fr;
        BufferedReader reader;

        file = new File(dataFilePathName.getPath()+userDataFileName);
        fr = new FileReader(file);
        reader = new BufferedReader(fr);

        line = reader.readLine();
        while (line != null) {
            data = line.replace("\n", "").split(",");
            HashMap<String, String> newUser = new HashMap<>();
            newUser.put("name", data[0]);
            newUser.put("email", data[1]);
            newUser.put("gender", data[2]);
            newUser.put("status", data[3]);
            userData.add(newUser);
            line = reader.readLine();
        }

        file = new File(dataFilePathName.getPath()+postDataFileName);
        fr = new FileReader(file);
        reader = new BufferedReader(fr);

        line = reader.readLine();
        while (line != null) {
            data = line.replace("\n", "").split(";");
            HashMap<String, String> newPost = new HashMap<>();
            newPost.put("title", data[0]);
            newPost.put("body", data[1]);
            postData.add(newPost);
            line = reader.readLine();
        }

        file = new File(dataFilePathName.getPath()+commentsDataFileName);
        fr = new FileReader(file);
        reader = new BufferedReader(fr);

        line = reader.readLine();
        while (line != null) {
            data = line.replace("\n", "").split(";");
            HashMap<String, String> newComment = new HashMap<>();
            newComment.put("post_id", data[0]);
            newComment.put("name", data[1]);
            newComment.put("email", data[2]);
            newComment.put("body", data[3]);
            commentData.add(newComment);
            line = reader.readLine();
        }

    }
}

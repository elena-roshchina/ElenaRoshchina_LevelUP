package ru.levelup.elena.roshchina.qa.homework_07;

import org.testng.annotations.BeforeMethod;
import ru.levelup.elena.roshchina.qa.homework_07.comments.CommentsPojo;
import ru.levelup.elena.roshchina.qa.homework_07.comments.SingleComment;
import ru.levelup.elena.roshchina.qa.homework_07.posts.PostsPojo;
import ru.levelup.elena.roshchina.qa.homework_07.posts.SinglePost;
import ru.levelup.elena.roshchina.qa.homework_07.users.SingleUser;
import ru.levelup.elena.roshchina.qa.homework_07.users.UsersPojo;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class AbstractGoRestAPITest {
    protected final String APIToken = "e2735da4aa6087a53f5374a398698fbf5aee5eb48da6944b899cc433ce9e1d2a";
    protected ArrayList<Map<String, String>> userData;
    protected ArrayList<Map<String, String>> postData;
    protected ArrayList<Map<String, String>> commentData;
    private final String userDataFileName = "user_data.csv";
    private final String postDataFileName = "post_data.csv";
    private final String commentsDataFileName = "comment_data.csv";
    private URL dataFilePathName = this.getClass().getResource("/ru.levelup.elena.roshchina.qa/homework_07/");

    protected int getUserId(String name, String email){
        UsersPojo usersPojo = given().get(Endpoints.getUserFilterUrl(name, email)).then().extract().body().as(UsersPojo.class);
        List<SingleUser> usersPojoData = usersPojo.getData();
        if (usersPojoData != null && usersPojoData.size() >0)
            return usersPojoData.get(0).getId();
        else
            return -1;
    }

    protected int getPostId(String title){
        PostsPojo postsPojo = given().get(Endpoints.getPostFilterUrl(title)).then().extract().body().as(PostsPojo.class);
        List<SinglePost> postsPojoData = postsPojo.getData();
        if (postsPojoData != null && postsPojoData.size() >0)
            return postsPojoData.get(0).getId();
        else
            return -1;
    }

    protected int getCommentId(String name, String postID, String email){
        CommentsPojo commentsPojo = given().get(Endpoints.getCommentFilterUrl(name, postID, email)).then().extract().body().as(CommentsPojo.class);
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

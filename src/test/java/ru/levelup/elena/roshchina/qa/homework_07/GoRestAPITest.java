package ru.levelup.elena.roshchina.qa.homework_07;

import groovyjarjarantlr4.v4.runtime.atn.ParseInfo;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelup.elena.roshchina.qa.homework_07.comments.SingleComment;
import ru.levelup.elena.roshchina.qa.homework_07.comments.CommentsPojo;
import ru.levelup.elena.roshchina.qa.homework_07.posts.SinglePost;
import ru.levelup.elena.roshchina.qa.homework_07.posts.PostsPojo;
import ru.levelup.elena.roshchina.qa.homework_07.users.SingleUser;
import ru.levelup.elena.roshchina.qa.homework_07.users.UsersPojo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.levelup.elena.roshchina.qa.homework_07.Endpoints.BASEURL;
import static org.testng.Assert.assertTrue;

public class GoRestAPITest extends AbstractGoRestAPITest {

    int userIndex = 1;
    int postIndex = 1;
    int commentIndex = 1;

    @Test
    public void aaAllUsersRetrieveTest() {
        given()
                .when()
                .get(BASEURL + Endpoints.USERSENDP)
                .then()
                .statusCode(200);

        List<SingleUser> usersPojoData = given().get(BASEURL + Endpoints.USERSENDP).then().extract().body().as(UsersPojo.class).getData();
        assertTrue(usersPojoData != null && usersPojoData.size() > 0);
        System.out.println(Integer.toString(usersPojoData.size()) + " users are found");
    }

    @Test
    public void aaAllPostsRetrieveTest() {
        given()
                .when()
                .get(BASEURL + Endpoints.POSTSENDP)
                .then()
                .statusCode(200);

        List<SinglePost> postsPojoData = given().get(BASEURL + Endpoints.POSTSENDP).then().extract().body().as(PostsPojo.class).getData();
        assertTrue(postsPojoData != null && postsPojoData.size() > 0);
        System.out.println(Integer.toString(postsPojoData.size()) + " posts are found");
    }

    @Test
    public void aaAllCommentsRetrieveTest() {
        given()
                .when()
                .get(BASEURL + Endpoints.COMMENTSENDP)
                .then()
                .statusCode(200);

        List<SingleComment> commentsPojoData = given().get(BASEURL + Endpoints.COMMENTSENDP).then().extract().body().as(CommentsPojo.class).getData();
        assertTrue(commentsPojoData != null && commentsPojoData.size() > 0);
        System.out.println(Integer.toString(commentsPojoData.size()) + " comments nnnnnnnnnn are found");
    }


    @Test
    public void aaCreateAUserTest() {
        Map<String, String> userSelected = userData.get(userIndex);

        given().auth().oauth2(APIToken)
                .contentType(ContentType.JSON)
                .body(userSelected)
                .when()
                .post(BASEURL + Endpoints.USERSENDP)
                .then()
                .statusCode(200)
                .body("data.name", Matchers.equalTo(userSelected.get("name")))
                .body("data.email", Matchers.equalTo(userSelected.get("email")))
                .body("data.gender", Matchers.equalTo(userSelected.get("gender")));
        int id = getUserId(userSelected.get("name"), userSelected.get("email"));
        if(id != -1){
            System.out.println("User " + userSelected.get("name") + " is just created, id = " + Integer.toString(id));
        } else {
            System.out.println("Ooops, we tried to create a user, but something went wrong.");
        }
    }

    @Test
    public void abGetACreatedUserTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        int id = getUserId(userSelected.get("name"), userSelected.get("email"));
        if(id != -1) {
            given()
                    .when()
                    .get(BASEURL + Endpoints.USERSENDP + "/" + Integer.toString(id))
                    .then()
                    .statusCode(200)
                    .body("data.name", Matchers.equalTo(userSelected.get("name")))
                    .body("data.email", Matchers.equalTo(userSelected.get("email")))
                    .body("data.gender", Matchers.equalTo(userSelected.get("gender")));
        } else {
            System.out.println("user " + userSelected.get("name") + " is not found");
        }
    }

    @Test
    public void acCreatePostTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        Map<String, String> postSelected = postData.get(postIndex);
        int userID = getUserId(userSelected.get("name"), userSelected.get("email"));
        int postID;
        if(userID != -1){
            postSelected.put("user_id", Integer.toString(userID));
            given().auth().oauth2(APIToken)
                    .contentType(ContentType.JSON)
                    .body(postSelected)
                    .when()
                    .post(BASEURL + Endpoints.POSTSENDP)
                    .then()
                    .statusCode(200)
                    .body("data.user_id", Matchers.equalTo(Integer.parseInt(postSelected.get("user_id"))))
                    .body("data.title", Matchers.equalTo(postSelected.get("title")))
                    .body("data.body", Matchers.equalTo(postSelected.get("body")));
            postID = getPostId(postSelected.get("title"));
            if (postID != -1){
                System.out.println("Post " + postSelected.get("title") + " is just created, id = " + Integer.toString(postID));
            } else {
                System.out.println("Ooops, we tried to create a post, but something went wrong");
            }
        } else {
            System.out.println("Ooops, we tried to create a post, but user not found at id=" + Integer.toString(userID));
        }
    }

    @Test
    public void adGetCreatedPostTest() {
        Map<String, String> postSelected = postData.get(postIndex);
        int postID = getPostId(postSelected.get("title"));
        if(postID != -1) {
            given()
                    .when()
                    .get(BASEURL + Endpoints.POSTSENDP + "/" + Integer.toString(postID))
                    .then()
                    .statusCode(200)
                    .body("data.title", Matchers.equalTo(postSelected.get("title")))
                    .body("data.body", Matchers.equalTo(postSelected.get("body")));
        } else {
            System.out.println("post " + postSelected.get("title") + " is not found");
        }
    }

    @Test
    public void addCreateAndGetCommentTest() {
        Map<String, String> commentSelected = commentData.get(commentIndex);
        given().auth().oauth2(APIToken)
                    .contentType(ContentType.JSON)
                    .body(commentSelected)
                    .when()
                    .post(BASEURL + Endpoints.COMMENTSENDP)
                    .then()
                    .statusCode(200)
                    .body("data.post_id", Matchers.equalTo(Integer.parseInt(commentSelected.get("post_id"))))
                    .body("data.name", Matchers.equalTo(commentSelected.get("name")))
                    .body("data.body", Matchers.equalTo(commentSelected.get("body")));

        int commID = getCommentId(commentSelected.get("name"), commentSelected.get("post_id"),commentSelected.get("email"));
        if(commID != -1){
            System.out.println("Comment " + Integer.toString(commID) + "just created");
            given()
                    .when()
                    .get(BASEURL + Endpoints.COMMENTSENDP + "/" + Integer.toString(commID))
                    .then()
                    .statusCode(200)
                    .body("data.post_id", Matchers.equalTo(Integer.parseInt(commentSelected.get("post_id"))))
                    .body("data.name", Matchers.equalTo(commentSelected.get("name")))
                    .body("data.body", Matchers.equalTo(commentSelected.get("body")));
        } else {
            System.out.println("Ooops, we tried to create a comment, but something went wrong" );
        }
    }

    @Test
    public void adeRemoveCommentTest() {
        Map<String, String> commentSelected = commentData.get(commentIndex);
        int commID = getCommentId(commentSelected.get("name"), commentSelected.get("post_id"),commentSelected.get("email"));
        if(commID != -1){
            given().auth().oauth2(APIToken)
                    .when()
                    .delete(BASEURL + Endpoints.COMMENTSENDP + "/" + Integer.toString(commID))
                    .then()
                    .statusCode(200);
            System.out.println("comment from " + commentSelected.get("name") + " was deleted");
        }
        else {
            System.out.println("comment from " + commentSelected.get("name") + " is not found");
        }
    }


    @Test
    public void aeModifyPostTest() {
        Map<String, String> postSelected = postData.get(postIndex);
        int postID = getPostId(postSelected.get("title"));
        if(postID != -1){
            postSelected.put("id", Integer.toString(postID));
            postSelected.put("title", postSelected.get("title") + ", edited 2");
            given().auth().oauth2(APIToken)
                    .contentType(ContentType.JSON)
                    .body(postSelected)
                    .when()
                    .put(BASEURL + Endpoints.POSTSENDP + "/" + Integer.toString(postID))
                    .then()
                    .statusCode(200)
                    .body("data.title", Matchers.equalTo(postSelected.get("title")))
                    .body("data.body", Matchers.equalTo(postSelected.get("body")));
        } else {
            System.out.println("user " + postSelected.get("name") + " is not found");
        }
    }

    @Test
    public void afRemovePostTest() {
        Map<String, String> postSelected = postData.get(postIndex);
        int postID = getPostId(postSelected.get("title"));
        System.out.println(postID);

        if(postID != -1){
            given().auth().oauth2(APIToken)
                    .when()
                    .delete(BASEURL + Endpoints.POSTSENDP + "/" + Integer.toString(postID))
                    .then()
                    .statusCode(200);
            System.out.println("post " + postSelected.get("title") + " was deleted");
        }
        else {
            System.out.println("post " + postSelected.get("title") + " is not found");
        }
    }

    @Test
    public void agModifyUserTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        int id = getUserId(userSelected.get("name"), userSelected.get("email"));
        if(id != -1){
            userSelected.put("name", userSelected.get("name") + ", esq.");
            given().auth().oauth2(APIToken)
                    .contentType(ContentType.JSON)
                    .body(userSelected)
                    .when()
                    .put(BASEURL + Endpoints.USERSENDP + "/" + Integer.toString(id))
                    .then()
                    .statusCode(200)
                    .body("data.name", Matchers.equalTo(userSelected.get("name")))
                    .body("data.email", Matchers.equalTo(userSelected.get("email")))
                    .body("data.gender", Matchers.equalTo(userSelected.get("gender")));
        } else {
            System.out.println("user " + userSelected.get("name") + " is not found");
        }
    }

    @Test
    public void ahRemoveUserTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        int id = getUserId(userSelected.get("name"), userSelected.get("email"));
        if(id != -1){
            given().auth().oauth2(APIToken)
                    .when()
                    .delete(BASEURL + Endpoints.USERSENDP + "/" + Integer.toString(id))
                    .then()
                    .statusCode(200);
            System.out.println("user " + userSelected.get("name") + " was deleted");
        }
        else {
            System.out.println("user " + userSelected.get("name") + " is not found");
        }
    }
}

package ru.levelup.elena.roshchina.qa.homework_07;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.levelup.elena.roshchina.qa.homework_07.Endpoints.BASEURL;
import static org.testng.Assert.assertTrue;
import static ru.levelup.elena.roshchina.qa.homework_07.Endpoints.POSTSENDP;

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

        List<SingleUser> usersData = given().get(BASEURL + Endpoints.USERSENDP).then().extract().body().as(Users.class).getData();
        assertTrue(usersData != null && usersData.size() > 0);
        System.out.println(Integer.toString(usersData.size()) + " users are found");
    }

    @Test
    public void aaAllPostsRetrieveTest() {
        given()
                .when()
                .get(BASEURL + Endpoints.POSTSENDP)
                .then()
                .statusCode(200);

        List<SinglePost> postsData = given().get(BASEURL + Endpoints.POSTSENDP).then().extract().body().as(Posts.class).getData();
        assertTrue(postsData != null && postsData.size() > 0);
        System.out.println(Integer.toString(postsData.size()) + " posts are found");
    }

    @Test
    public void aaAllCommentsRetrieveTest() {
        given()
                .when()
                .get(BASEURL + Endpoints.COMMENTSENDP)
                .then()
                .statusCode(200);
        List<SingleComment> commentsData = given().get(BASEURL + Endpoints.COMMENTSENDP).then().extract().body().as(Comments.class).getData();
        assertTrue(commentsData != null && commentsData.size() > 0, "comments are not found");
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
        assertTrue(id > 0, "We tried to create a user, but something went wrong");
    }

    @Test
    public void abGetACreatedUserTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        int id = getUserId(userSelected.get("name"), userSelected.get("email"));
        assertTrue(id > 0, "User not found");
        given()
                .when()
                .get(BASEURL + Endpoints.USERSENDP + "/" + Integer.toString(id))
                .then()
                .statusCode(200)
                .body("data.name", Matchers.equalTo(userSelected.get("name")))
                .body("data.email", Matchers.equalTo(userSelected.get("email")))
                .body("data.gender", Matchers.equalTo(userSelected.get("gender")));
    }

    @Test
    public void acCreatePostTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        Map<String, String> postSelected = postData.get(postIndex);
        int userID = getUserId(userSelected.get("name"), userSelected.get("email"));
        assertTrue(userID > 0, "User does not exist");
        int postID;

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
        assertTrue(postID > 0,  "We tried to create a post, but something went wrong");
    }

    @Test
    public void adGetCreatedPostTest() {
        Map<String, String> postSelected = postData.get(postIndex);
        int postID = getPostId(postSelected.get("title"));
        assertTrue(postID > 0, "Post \"" + postSelected.get("title") + "\" is not exist");
        given()
                .when()
                .get(BASEURL + Endpoints.POSTSENDP + "/" + Integer.toString(postID))
                .then()
                .statusCode(200)
                .body("data.title", Matchers.equalTo(postSelected.get("title")))
                .body("data.body", Matchers.equalTo(postSelected.get("body")));
    }

    @Test
    public void addCreateAndGetCommentTest() {
        Map<String, String> commentSelected = commentData.get(commentIndex);
        given()
                .when()
                .get(BASEURL + Endpoints.POSTSENDP + "/" + commentSelected.get("post_id"))
                .then()
                .statusCode(200);

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
        assertTrue(commID > 0, "we tried to create a comment, but something went wrong");

        given()
                .when()
                .get(BASEURL + Endpoints.COMMENTSENDP + "/" + Integer.toString(commID))
                .then()
                .statusCode(200)
                .body("data.post_id", Matchers.equalTo(Integer.parseInt(commentSelected.get("post_id"))))
                .body("data.name", Matchers.equalTo(commentSelected.get("name")))
                .body("data.body", Matchers.equalTo(commentSelected.get("body")));
    }

    @Test
    public void adeRemoveCommentTest() {
        Map<String, String> commentSelected = commentData.get(commentIndex);
        int commID = getCommentId(commentSelected.get("name"), commentSelected.get("post_id"),commentSelected.get("email"));
        assertTrue(commID > 0, "comment is not exist");
        given().auth().oauth2(APIToken)
                .when()
                .delete(BASEURL + Endpoints.COMMENTSENDP + "/" + Integer.toString(commID))
                .then()
                .statusCode(200);
    }


    @Test
    public void aeModifyPostTest() {
        Map<String, String> postSelected = postData.get(postIndex);
        int postID = getPostId(postSelected.get("title"));
        assertTrue(postID > 0, "Post \"" + postSelected.get("title") + "\" is not exist");

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
    }

    @Test
    public void afRemovePostTest() {
        Map<String, String> postSelected = postData.get(postIndex);
        int postID = getPostId(postSelected.get("title"));
        assertTrue(postID > 0, "Post \"" + postSelected.get("title") + "\" is not exist");

        given().auth().oauth2(APIToken)
                .when()
                .delete(BASEURL + Endpoints.POSTSENDP + "/" + Integer.toString(postID))
                .then()
                .statusCode(200);
    }

    @Test
    public void agModifyUserTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        int id = getUserId(userSelected.get("name"), userSelected.get("email"));
        assertTrue(id > 0, "User is not exist");

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
    }

    @Test
    public void ahRemoveUserTest() {
        Map<String, String> userSelected = userData.get(userIndex);
        int id = getUserId(userSelected.get("name"), userSelected.get("email"));
        assertTrue(id > 0, "User is not exist");

        given().auth().oauth2(APIToken)
                .when()
                .delete(BASEURL + Endpoints.USERSENDP + "/" + Integer.toString(id))
                .then()
                .statusCode(200);
    }
}

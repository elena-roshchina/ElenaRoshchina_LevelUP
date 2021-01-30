package ru.levelup.elena.roshchina.qa.homework_07;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.levelup.elena.roshchina.qa.homework_07.users.SingleUser;
import ru.levelup.elena.roshchina.qa.homework_07.users.UsersPojo;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.levelup.elena.roshchina.qa.homework_07.Endpoints.BASEURL;
import static org.testng.Assert.assertTrue;

public class GoRestAPITest extends AbstractGoRestAPITest {

    int userIndex = 0;

    @Test
    public void allUsersRetrieveTest() {
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
    public void createUserTest() {
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
    public void getCreatedUserTest() {
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
    public void modifyUserTest() {
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
    public void removeUserTest() {
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

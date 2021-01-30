package ru.levelup.elena.roshchina.qa.homework_07;

import org.testng.annotations.BeforeMethod;
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

    protected int getUserId(String name, String email){
        UsersPojo usersPojo = given().get(Endpoints.getUserFilterUrl(name, email)).then().extract().body().as(UsersPojo.class);
        List<SingleUser> usersPojoData = usersPojo.getData();
        if (usersPojoData != null && usersPojoData.size() >0)
            return usersPojo.getData().get(0).getId();
        else
            return -1;
    }

    @BeforeMethod
    public void setUpUserData() throws IOException {
        URL dataFilepathName = this.getClass().getResource("/ru.levelup.elena.roshchina.qa/homework_07/user_data.csv");
        File file = new File(dataFilepathName.getPath());
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String[] data;
        userData = new ArrayList<>();
        String line = reader.readLine();
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
    }
}

package testcases;

import data.TestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class BackendTestcases {

    // Below tests are to cover the Posts endpoint

    @Test(dataProvider = "addPost", dataProviderClass = TestData.class)
    public void createPost(String title, String body, int userId) {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);
        requestParams.put("body", body);
        requestParams.put("userId", userId);

        int id=given().
                body(requestParams.toJSONString()).
                when().
                post("/posts").
                then().assertThat().
                statusCode(201).and().
                contentType(ContentType.JSON).and()
                .body("title",equalTo(requestParams.get(title)))
                .body("body", equalTo(requestParams.get(body)))
                .body("userId", equalTo(requestParams.get(userId))).
                extract().
                path("id");

        Assert.assertEquals(id,101);


    }

    @Test(dataProvider = "updatePost", dataProviderClass = TestData.class)
    public void updatePost(int id,String title, String body, int userId) {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", id);
        requestParams.put("title", title);
        requestParams.put("body", body);
        requestParams.put("userId", userId);

        int responseId=given().
                body(requestParams.toJSONString()).
                when().
                put("/posts/" +id).
                then().assertThat().
                statusCode(200).and().
                contentType(ContentType.JSON).and()
                .body("title",equalTo(requestParams.get(title)))
                .body("body", equalTo(requestParams.get(body)))
                .body("userId", equalTo(requestParams.get(userId))).
                        extract().
                        path("id");

        Assert.assertEquals(responseId,100);


    }

    @Test(dataProvider = "userId", dataProviderClass = TestData.class)
    public void filterUsers(int userId){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        Response response= given().queryParam("userId", userId)
                .get("/posts").then().assertThat().statusCode(200).extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains(String.valueOf(userId)), true);

    }

    @Test(dataProvider = "postId", dataProviderClass = TestData.class)
    public void deletePost(int postId){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        given()
                .delete("/posts/"+postId).then().assertThat().statusCode(200).and()
                .body("isEmpty()", Matchers.is(true)).extract().response();

    }

    @Test(dataProvider = "updateInvalid", dataProviderClass = TestData.class)
    public void updatePostWithInvalidId(int id,String title, String body, int userId) {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        JSONObject requestParams = new JSONObject();
        requestParams.put("id", id);
        requestParams.put("title", title);
        requestParams.put("body", body);
        requestParams.put("userId", userId);

        given().
                body(requestParams.toJSONString()).
                when().
                put("/posts/" +id).
                then().assertThat().
                statusCode(500);
    }

    @Test(dataProvider = "invalidUserId", dataProviderClass = TestData.class)
    public void filterUsersFromInvalidUserId(int userId){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        Response response= given().queryParam("userId", userId)
                .get("/posts").then().assertThat().statusCode(200).and().body("isEmpty()", Matchers.is(true)).extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains(String.valueOf(userId)), false);

    }

    @Test(dataProvider = "userId", dataProviderClass = TestData.class)
    public void timeTakentoFilterUsers(int userId){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        Response response= given().queryParam("userId", userId)
                .get("/posts").then().assertThat().statusCode(200).time(lessThan(1000L)).extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains(String.valueOf(userId)), true);

    }

    // Below tests are to cover the Users end point

    @Test(dataProvider = "addUser", dataProviderClass = TestData.class)
    public void addUser(String name, String username, String email, String street,
                        String suite, String city, String zip,
                        String lat, String lng, String phone, String website,
                        String compName, String compCatch, String compBs) {

        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        Map<String,String> geo = new HashMap<>();
        geo.put("lat", lat);
        geo.put("lng", lng);

        Map<String,String> address = new HashMap<>();
        address.put("street", street);
        address.put("city", city);
        address.put("zip", zip);
        address.put("lat",geo.get(lat) );
        address.put("lng",geo.get(lng) );

        Map<String,String> company = new HashMap<>();
         company.put("name", name);
         company.put("catchPhrase", compCatch);
         company.put("bs", compBs);

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("username", username);
        requestParams.put("email", email);
        requestParams.put("address", address);
        requestParams.put("phone", phone);
        requestParams.put("website",website);
        requestParams.put("company",company);

        int id=given().
                body(requestParams.toJSONString()).
                when().
                post("/users").
                then().assertThat().
                statusCode(201).and().
                contentType(ContentType.JSON)
                .extract()
                .path("id");

        Assert.assertEquals(id,11);
    }

    @Test(dataProvider = "userId", dataProviderClass = TestData.class)
    public void gettUser(int userId){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        Response response= given().queryParam("id", userId)
                .get("/users").then().assertThat().statusCode(200).extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains(String.valueOf(userId)), true);

    }

    @Test(dataProvider = "userId", dataProviderClass = TestData.class)
    public void deleteUser(int userId){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        given()
                .delete("/users/"+userId).then().assertThat().statusCode(200).and()
                .body("isEmpty()", Matchers.is(true)).extract().response();
    }

    @Test(dataProvider = "updateUser", dataProviderClass = TestData.class)
    public void updateUser(int id,String name, String username, String email, String street,
                           String suite, String city, String zip,
                           String lat, String lng, String phone, String website,
                           String compName, String compCatch, String compBs) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        Map<String,String> geo = new HashMap<>();
        geo.put("lat", lat);
        geo.put("lng", lng);

        Map<String,String> address = new HashMap<>();
        address.put("street", street);
        address.put("city", city);
        address.put("zip", zip);
        address.put("lat",geo.get(lat) );
        address.put("lng",geo.get(lng) );

        Map<String,String> company = new HashMap<>();
        company.put("name", name);
        company.put("catchPhrase", compCatch);
        company.put("bs", compBs);



        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("username", username);
        requestParams.put("email", email);
        requestParams.put("address", address);
        requestParams.put("phone", phone);
        requestParams.put("website",website);
        requestParams.put("company",company);

        int responseId = given().
                body(requestParams.toJSONString()).
                when().
                put("/users/" + id).
                then().assertThat().
                statusCode(200).and().
                contentType(ContentType.JSON).
                        extract().
                        path("id");

        Assert.assertEquals(responseId, 2);
    }

    @Test(dataProvider = "userId", dataProviderClass = TestData.class)
    public void timeTakeByGetUser(int userId){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";

        Response response= given().queryParam("id", userId)
                .get("/users").then().assertThat().statusCode(200).time(lessThan(1000L)).extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains(String.valueOf(userId)), true);
    }
}




package testcases;

import data.TestData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;



public class BackendTestcases {

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






    }




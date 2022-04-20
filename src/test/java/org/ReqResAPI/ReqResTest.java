package org.ReqResAPI;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class ReqResTest {
    private String url = "https://reqres.in/api/users";
    private String dados = "{\"name\":\" Fabio \", \"job\":\"QA\"}";

    @Test
    public void getPageOneTest(){
        given().
                param("page", "1").
                when().
                get(url).
                then().
                statusCode(200).
                body("page", equalTo(1));
    }

    @Test
    public void getUserTest() {
        get(url + "/2").then().body("data.id", equalTo(2));
    }

    @Test
    public void postUserTest(){

        given().
                body(dados).
                when().
                post(url).
                then().
                statusCode(201).
                body(containsString("createdAt"));
    }

    @Test
    public void putUserTest(){

        given().
                body(dados).
                when().
                put(url + "/2").
                then().
                statusCode(200).
                body(containsString("updatedAt"));
    }

    @Test
    public void deleteUserTest(){
        when().
                delete(url + "/2").
                then()
                .statusCode(204);
    }
}

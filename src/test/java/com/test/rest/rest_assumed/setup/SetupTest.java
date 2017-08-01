package com.test.rest.rest_assumed.setup;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.BeforeClass;

import static com.test.controller.TokenController.TokenResponse;
import static com.jayway.restassured.RestAssured.given;

public class SetupTest {

    public static String adminToken;
    //"eyJhbGciOiJIUzI1NiJ9.eyJsb2dpbiI6ImFkbWluIn0.Va6fMB7wfo1rS9o13cn54qRG70hyl1qmoLRpbb7dKs8"

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.defaultParser = Parser.JSON;

        RestAssured.basePath = "/token";
        adminToken = adminTokenGenerate();

        RestAssured.basePath = "/user";
    }


    public static String adminTokenGenerate() {
        return given()
                    .queryParam("login", "admin")
                    .queryParam("password", "admin")
                .when()
                    .get()
                .as(TokenResponse.class)
                    .getToken();
    }

}

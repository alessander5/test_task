package com.test.rest.rest_assumed;

import com.test.rest.rest_assumed.setup.SetupTest;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class AuthTest extends SetupTest {

    @Test
    public void authRequired() {
        given()
        .when()
                .get()
        .then()
                .statusCode(401);
    }

    @Test
    public void paramAuth(){
        given()
                .queryParam("token", adminToken)
        .when()
                .get()
        .then()
                .statusCode(200);
    }

    @Test
    public void headerAuth(){
        given()
                .header("token", adminToken)
        .when()
                .get()
        .then()
                .statusCode(200);
    }

    @Test
    public void cookieAuth(){
        given()
                .cookie("token", adminToken)
        .when()
                .get()
        .then()
                .statusCode(200);
    }

}

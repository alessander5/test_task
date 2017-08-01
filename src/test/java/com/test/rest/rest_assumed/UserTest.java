package com.test.rest.rest_assumed;

import com.test.domain.User;
import com.test.rest.rest_assumed.setup.SetupTest;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.test.domain.UserRoleEnum.ROLE_ADMIN;
import static com.test.domain.UserRoleEnum.ROLE_USER;

public class UserTest extends SetupTest {

    private User userMock = new User();

    // need to manually set for update/remove by id
    private long id = -1;

    {
        userMock.setId(id);
        userMock.setUserName("mock");
        userMock.setPassWord("mock");
        userMock.setRoles(ROLE_ADMIN, ROLE_USER);
        userMock.setActive(true);
    }


    @Test
    public void addAndUpdateUser() {
        given()
                .queryParam("token", adminToken)
                .contentType("application/json")
                .body(userMock)
        .when()
                .post()
        .then()
                .statusCode(200);

        userMock.setPassWord("mock2");
        given()
                .queryParam("token", adminToken)
                .contentType("application/json")
                .body(userMock)
        .when()
                .put()
        .then()
                .statusCode(200);
    }

    @Test
    public void removeUserById(){
        given()
                .queryParam("token", adminToken)
                .queryParam("id", id)
        .when()
                .delete()
        .then()
                .statusCode(200);
    }

}

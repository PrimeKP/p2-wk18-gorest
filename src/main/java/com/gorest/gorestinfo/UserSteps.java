package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step("Creating user with name : {0}, email: {1}, gender: {2} and status: {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .when()
                .body(userPojo)
                .post(EndPoints.GET_ALL_USERS)
                .then().log().all().statusCode(201);
    }

    @Step("Verifying user is created with userID : {0}")
    public HashMap<String, Object> getUserInfoByUserID(int userID) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .when()
                .pathParam("userID", userID)
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("Updating user with userID : {0}, name : {1}, email: {2}, gender: {3} and status: {4}")
    public ValidatableResponse updateUser(int userID, String name, String email, String gender, String status) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .pathParam("userID", userID)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Step("Deleting user information with userID: {0}")
    public ValidatableResponse deleteUser(int userID) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting user information with userID: {0}")
    public ValidatableResponse getUserById(int userID){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 6b414b347fd2fe75af1eeb80a68c93611789852e3d838d0bd9d315b441e79756")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.DELETE_USER_BY_ID)
                .then();
    }


}

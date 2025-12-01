package com.solvd.carinaapitesting;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetPosts() {
        given()
            .when()
                .get("/posts")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void testGetPost() {
        given()
            .pathParam("id", 1)
            .when()
                .get("/posts/{id}")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }

    @Test
    public void testCreatePost() {
        String postBody = "{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
            .contentType(ContentType.JSON)
            .body(postBody)
            .when()
                .post("/posts")
            .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    public void testUpdatePost() {
        String updateBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"foo updated\",\n" +
                "  \"body\": \"bar updated\",\n" +
                "  \"userId\": 1\n" +
                "}";

        given()
            .pathParam("id", 1)
            .contentType(ContentType.JSON)
            .body(updateBody)
            .when()
                .put("/posts/{id}")
            .then()
                .statusCode(200)
                .body("title", equalTo("foo updated"));
    }

    @Test
    public void testDeletePost() {
        given()
            .pathParam("id", 1)
            .when()
                .delete("/posts/{id}")
            .then()
                .statusCode(200)
                .body("size()", equalTo(0));
    }
}

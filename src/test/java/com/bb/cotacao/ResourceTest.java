package com.bb.cotacao;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class ResourceTest {

	@Test
	public void testEndPoint() {
		given().when().port(8080).get("/api/cotacao").then().statusCode(200).body(containsString("date"));
	}
}
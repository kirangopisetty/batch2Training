package com.api.testing;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;

public class getListUsersAPI {
	
	@Test
	public void listUsers() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users")
		
		.then()
		//	.log().all()
			.log().status()
			.time(Matchers.greaterThan(100L))
			.log().body()		// print response body
			.log().headers()	// print response headers
			.statusCode(200)	// verify if statusCode=200
			.body("gender", hasItems("male","female"))
			.body("status", hasItems("active","inactive"))
		//	.body("[0].name", equalTo("Kiran"))
		//	.body("[1].gender", equalTo("male"))
		//	.body("[5].status", equalTo("inactive"))
			.header("Content-Type", "application/json; charset=utf-8");
	}
}
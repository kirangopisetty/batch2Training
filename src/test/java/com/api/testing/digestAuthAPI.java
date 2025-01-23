package com.api.testing;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class digestAuthAPI {

	@Test
	public void digestAuthAPI1() {
		
		given()
			.header("accept","application/json")
			.auth().digest("user", "passwd")
				
		.when()
			.get("https://httpbin.org/digest-auth/auth/user/passwd")
	
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.header("content-type", "application/json")
			.body("authenticated", equalTo(true))
			.assertThat().body("authenticated", oneOf(true, false))
			.log().body();
	}	
	
	@Test
	public void digestAuthAPI2() {
		
		given()
			.header("accept","application/json")
			.auth().digest("user", "passwd")
				
		.when()
			.get("https://httpbin.org/digest-auth/auth/user/passwd/MD5")
	
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(4000L))
			.header("content-type", "application/json")
			.body("authenticated", equalTo(true))
			.assertThat().body("authenticated", oneOf(true, false))
			.log().body();
	}
	
	@Test
	public void digestAuthAPI3() {
		
		given()
			.header("accept","application/json")
			.auth().digest("user", "passwd")
				
		.when()
			.get("https://httpbin.org/digest-auth/auth/user/passwd/SHA-256/never")
	
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(4000L))
			.header("content-type", "application/json")
			.body("authenticated", equalTo(true))
			.assertThat().body("authenticated", oneOf(true, false))
			.log().body();
	}
	
}
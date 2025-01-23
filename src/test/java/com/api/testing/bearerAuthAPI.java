package com.api.testing;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class bearerAuthAPI {
	
	@Test
	public void bearerAuthTokenAPI() {
		
		given()
			.header("Accept", "application/json")
			.header("Authorization", "Bearer 12345")
		
		.when()
			.get("https://httpbin.org/bearer")
			
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(4000L))
			.header("content-type", "application/json")
			.body("authenticated", equalTo(true))
			.assertThat().body("authenticated", oneOf(true, false))
			.log().body();
	}
}
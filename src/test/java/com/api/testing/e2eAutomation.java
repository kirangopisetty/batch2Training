package com.api.testing;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import static org.hamcrest.Matchers.*;

@Epic("EPIC:E2E API AUTOMATION")
@Feature("FEATURE:The feature objective is to achieve end to end api chaining automation across all 6 APIs")
public class e2eAutomation {
	
	// CREATE A USER (POST) 
	
	Faker faker = new Faker();
	int extractedID;
		
	@Story("User Story: The application allows the user to create a user")
	@Step("Step:Enter the name, email, gender & status and submit the request")
	@Description("Description:This API uses random java functions to generate test data")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 1)
	public void createAuser() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().firstName());
		requestBody.put("gender", faker.demographic().sex());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "active");
		
		extractedID=given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.post("https://gorest.co.in/public/v2/users").
			jsonPath().getInt("id");   // to extract id from the response body
			
			System.out.println("The id created is >> "+extractedID);
			System.out.println("CREATE A USER API = PASSED");
			
	//	.then()
	//		.statusCode(201)
	//		.log().status()
	//		.log().body();
	}
	
	// VERIFY IF USER IS CREATED (GET) 
	
	@Story("User Story: The application allows the user to verify if the user is created")
	@Step("Step:Enter the id of the person and submit the request")
	@Description("Description:This API uses the extracted ID to generate the user details")
	@Severity(SeverityLevel.MINOR)
	@Test (priority = 2)
	public void verifyIfUserisCreated() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().body()
			.body("id", equalTo(+extractedID));
			System.out.println("The ID we are verifying to check if its created is "+extractedID);
			System.out.println("VERIFY IF USER IS CREATED API = PASSED");
}
	
	// UPDATE THE CREATED USER (PATCH)
	
	@Story("User Story: The application allows the user to update an user")
	@Step("Enter the id, name, email, gender & status and submit the request")
	@Description("This API uses random java functions to generate test data")
	@Severity(SeverityLevel.CRITICAL)
	@Test (priority = 3)
	public void updateTheCreatedUser() {
		
		HashMap<String, String> requestBody = new HashMap<String, String>();
		requestBody.put("name", faker.name().prefix()+" "+faker.name().lastName());
		requestBody.put("email", faker.internet().emailAddress());
		requestBody.put("status", "inactive");
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.body(requestBody)
			.patch("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().body();
			System.out.println("The id updated is >> "+extractedID);
			System.out.println("VERIFY IF USER IS UPDATED API = PASSED");
	}
	
	
	// VERIFY IF USER IS UPDATED (GET) 
	@Story("User Story: The application allows the user to verify if the user is updated")
	@Step("Enter the id and submit the request")
	@Description("This API uses the extracted ID to generate the updated user details")
	@Severity(SeverityLevel.MINOR)
	@Test (priority = 4)
	public void verifyIfUserisUpdated() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.log().body()
			.body("id", equalTo(+extractedID));
			System.out.println("The ID we are verifying to check if its updated is "+extractedID);
			System.out.println("VERIFY IF USER IS UPDATED API = PASSED");
}
	
	// DELETE THE CREATED USER (DELETE) 
	@Story("User Story: The application allows the user to delete the user")
	@Step("Enter the id and submit the request")
	@Description("This API uses the extracted ID to delete the user")
	@Severity(SeverityLevel.BLOCKER)
	@Test (priority = 5)
	public void deleteTheCreatedUser() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")

		.when()
			.delete("https://gorest.co.in/public/v2/users/"+extractedID)
		
		.then()
			.statusCode(204)
			.log().status();
			System.out.println("The id deleted is >> "+extractedID);
			System.out.println("VERIFY IF USER IS DELETED API = PASSED");
	}
	
	// VERIFY IF USER IS DELETED (GET)
	
	@Story("User Story: The application allows the user to verify if the user is deleted")
	@Step("Enter the id and submit the request")
	@Description("This API uses extracted ID to verify if the user is deleted")
	@Severity(SeverityLevel.MINOR)
	@Test (priority = 6)
	public void verifyIfUserisDeleted() {
		
		given()
			.header("Accept","application/json")
			.header("Content-Type","application/json")
			.header("Authorization","Bearer a1acf13036e08546446ecbcbeb75b11959fbfcc0795218a185cfc982f6982c29")
		
		.when()
			.get("https://gorest.co.in/public/v2/users")
		
		.then()
			.log().body()
			.body("id", not(equalTo(+extractedID)));
			// body("[0].id", not(equalTo(+extractedID)));
			System.out.println("The ID we are verifying to check if its deleted is "+extractedID);
			System.out.println("VERIFY IF USER IS DELETED API = PASSED");
}
}
package com.api.testing;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class soapAPIs {

	@Test
	public void addAPI() throws IOException {
		
		File file = new File("./src/test/resources/addRequestBody.xml");
		FileInputStream fis = new FileInputStream(file);		// read file contents in byte[] format
		String requestBody = IOUtils.toString(fis, "UTF-8");	// converts byte[] into string format
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Add")
		//	.pathParam("pathParam1", "/calculator.asmx ")
		
		.when()
			.body(requestBody)
		//	.post("http://www.dneonline.com/{pathParam1}")
			.post("http://www.dneonline.com/calculator.asmx")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.body("//*:AddResult.text()", equalTo("31"))
			.header("Content-Type", "text/xml; charset=utf-8")
			.log().body();
	}
	
	@Test
	public void subtractAPI() throws IOException {
		
		File file = new File("./src/test/resources/subtractRequestBody.xml");
		FileInputStream fileInputStream = new FileInputStream(file);	// read as bytes
		String requestBody = IOUtils.toString(fileInputStream, "UTF-8"); // convert bytes to string format
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Subtract")
		
		.when()
			.body(requestBody)
			.post("http://www.dneonline.com/calculator.asmx")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.body("//*:SubtractResult.text()", equalTo("40"))
			.header("Content-Type", "text/xml; charset=utf-8")
			.log().body();
	}
	
	
	@Test
	public void multiplyAPI() throws IOException {
		
		File file = new File("./src/test/resources/multiplyRequestBody.xml");
		FileInputStream fis = new FileInputStream(file);
		String requestBody = IOUtils.toString(fis, "UTF-8");
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Multiply")
		
		.when()
			.body(requestBody)
			.post("http://www.dneonline.com/calculator.asmx")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.body("//*:MultiplyResult.text()", equalTo("500"))
			.header("Content-Type", "text/xml; charset=utf-8")
			.log().body();
	}
	
	
	@Test
	public void divideAPI() throws IOException {
		
		File file = new File("./src/test/resources/divideRequestBody.xml");
		FileInputStream fis = new FileInputStream(file);
		String requestBody = IOUtils.toString(fis, "UTF-8");
		
		given()
			.header("Content-Type","text/xml; charset=utf-8")
			.header("SOAPAction","http://tempuri.org/Divide")
		
		.when()
			.body(requestBody)
			.post("http://www.dneonline.com/calculator.asmx")
		
		.then()
			.statusCode(200)
			.time(Matchers.lessThan(3000L))
			.body("//*:DivideResult.text()", equalTo("30"))
			.header("Content-Type", "text/xml; charset=utf-8")
			.log().body();
	}	
}
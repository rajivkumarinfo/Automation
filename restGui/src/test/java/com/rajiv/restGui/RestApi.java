package com.rajiv.restGui;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;


@Test
public class RestApi {
	public void verify()
	{
	
		int i = 1;
		while(i>0)
		{
		Response s = given().params("valid_password", "bar").params("valid_username", "foo").when()
				.get("https://api.github.com/user");

		int scode= s.getStatusCode();
		System.out.println("Response Code is -----> "+ scode);
		
		if (scode==403)
		{
			System.out.println("Headers---->  " + s.getHeaders().toString());
			System.out.println("Response-----> " + s.then().extract().response().asString());
			break;
		}
		
		i++;
		}
		
		
	}
}

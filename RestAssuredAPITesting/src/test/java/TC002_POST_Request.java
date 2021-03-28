import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {
	@Test
	void PostDataSuccess()
	{
		//Specify base URI
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		
		
		//Request payload sending along with post request
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("title","recommendation");
		requestParams.put("body","motorcycle");
		requestParams.put("userId", "12");
		requestParams.put("id", "101");
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString()); //attach above data to the request
		
		//Response Object
		Response response= httpRequest.request(Method.POST,"/posts");
		
		 
		//Print Response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		//status code validation
		int statusCode= response.getStatusCode();
		System.out.println("status code is: "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		//success code validation
		String titleexpected = response.jsonPath().get("title");
		Assert.assertEquals(titleexpected, "recommendation");
		
		String bodyexpected = response.jsonPath().get("body");
		Assert.assertEquals(bodyexpected, "motorcycle");
		
		String useridexpected = response.jsonPath().get("userId");
		Assert.assertEquals(useridexpected, "12");
	
		
	}
}

	



import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC001_GET_REQUEST {

	@Test
	void getDataPosts()
	{
		//Specify base URI
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response Object
		Response response= httpRequest.request(Method.GET,"/posts");
		 
		//Print Response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		
		//status code validation
		int statusCode= response.getStatusCode();
		System.out.println("status code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//status line verification
		String statusLine = response.getStatusLine();
		System.out.println("status line is: "+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		//validating header
		String contentType = response.header("Content-Type"); //capture data from header
		System.out.println("Content Type is:" +contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
		
		//validating type data
		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("userId"));
	
		
		
		
		
		
	}
}

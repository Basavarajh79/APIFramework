package stepDefinations;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils{
	
	RequestSpecification res;
	ResponseSpecification resp;
	Response responce;
	TestDataBuild data=new TestDataBuild();
	static String place_id;
	JsonPath js;
	
	
	
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String Langauge, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
				res=given().spec(requestSpecification()).body(data.addPlacePayLoad(name, Langauge, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String string, String method) {
		 //constructor of will be called for API resources(APIResources)
		 APIResources resourceAPI=APIResources.valueOf(string);
		 System.out.println(resourceAPI.getResource());
		 System.out.println(method);
		 resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	
		 if(method.equalsIgnoreCase("POST")) {
		 responce=res.when().post(resourceAPI.getResource());
				
		 }else if(method.equalsIgnoreCase("GET")){
			 responce=res.when().get(resourceAPI.getResource());
		 }
			 
		 }
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(responce.getStatusCode(), 200);
		}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	    
	    assertEquals(getJosonPath(responce, key), value);   
	}
	@Then("verify place_Id created maps {string} using {string}")
	public void verify_place_id_created_maps_using(String expName, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJosonPath(responce,"place_id");
	    res=given().spec(requestSpecification()).queryParam("place_id", place_id);
	    user_calls_with_http_request(resource,"GET");
	    String ActualName=getJosonPath(responce,"name");
	    assertEquals(expName,ActualName);
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    
	 res=  given().spec(requestSpecification()).body(data.deletePLacePayload(place_id));
	    
	}
}
package stepDefinations;

import java.io.IOException;

import javax.activation.MailcapCommandMap;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		stepDefination m=new stepDefination();
		if(stepDefination.place_id==null) {
		m.add_place_payload_with("Basavaraj", "French", "Asia");
		m.user_calls_with_http_request("ADDPlaceAPI", "POST");
		m.verify_place_id_created_maps_using("Basavaraj", "getPlaceAPI");
		
	}
}}

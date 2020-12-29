package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name,String langauge,String address) {
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setName(name);
		p.setAddress(address);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setLanguage(langauge);
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location loc=new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		p.setLocation(loc);
		return p;
	}
	public String deletePLacePayload(String place_id) {
		return "\r\n{\r\n    \"place_id\":\""+place_id+"\"   \t \t}\r\n";
	}
}

Feature: Validating Place API's



@AddPlace
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
Given Add place Payload with "<name>" "<language>" "<address>"
When user calls "ADDPlaceAPI" with "post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps "<name>" using "getPlaceAPI" 
  
  
  
Examples:

  | name    | language  | address|
  | AAhouse | Kannada   | world2 |
# | BBhouse | Spanish   | new yark| 
 

@DeletePlace
Scenario: Verify if Delete place functionality is working


Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
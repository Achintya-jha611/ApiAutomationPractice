package TestClasses;
import JsonFiles.AddPlaces;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SampleTest {
public static void main(String args[]){
    RestAssured.baseURI= "https://rahulshettyacademy.com";
    String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
            .body(AddPlaces.jsonBody()).when().post("maps/api/place/add/json")
            .then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("content-type",equalTo( "application/json;charset=UTF-8")).extract().response().asString();
    //System.out.println(response);// Storing response as string

    JsonPath jp;//Parsing json response
    jp = new JsonPath(response);
    String placeIdResponseVal=jp.get("place_id");
    System.out.println(placeIdResponseVal);

}}

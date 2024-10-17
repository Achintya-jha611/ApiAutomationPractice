package TestClasses;
import JsonFiles.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SampleTest {
public static void main(String args[]){
    RestAssured.baseURI= "https://rahulshettyacademy.com";
    String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
            .body(payloads.jsonBody()).when().post("maps/api/place/add/json")
            .then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("content-type",equalTo( "application/json;charset=UTF-8")).extract().response().asString();
    //System.out.println(response);// Storing response as string

    JsonPath jp;//Parsing json response
    jp = new JsonPath(response);
    String placeIdResponseVal=jp.get("place_id");
    System.out.println(placeIdResponseVal);

    String newAddress = "Summer Walk, Africa";

    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
            .body("{\r\n" +
                    "\"place_id\":\""+placeIdResponseVal+"\",\r\n" +
                    "\"address\":\""+newAddress+"\",\r\n" +
                    "\"key\":\"qaclick123\"\r\n" +
                    "}").
            when().put("maps/api/place/update/json")
            .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

    //Put call by passing updated values in response

    String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
            .queryParam("place_id",placeIdResponseVal)
            .when().get("maps/api/place/get/json")
            .then().assertThat().log().all().statusCode(200).extract().response().asString();
    JsonPath js1=new JsonPath(getPlaceResponse);
    String actualAddress =js1.getString("address");
    System.out.println(actualAddress);
    Assert.assertEquals(actualAddress, "Summer Walk, Africa");
    //making a get call and extracting the response and then validating value corresponding to a key to figure out whether we have updated value for the key or not

}}

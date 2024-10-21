package TestClasses;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestClassForSerializationUsingPojo {
    @Test public static void SerializeRequest(){
   RestAssured.baseURI="https://rahulshettyacademy.com";
   AddPlaceGoogleMaps a=new AddPlaceGoogleMaps();//create object of parent class
   Locations l=new Locations();
   l.setLat(23.00);
   l.setLng(20.00);//call setter methods and add values for all fields
   a.setLocation(l);
   a.setName("testing place");
   a.setAccuracy(50);
   a.setAddress("Random test place");
   a.setPhone_number("1323001211");
   List<String> types=new ArrayList<String>();
   types.add("permanent");
   types.add("temporary");
   a.setTypes(types);
   a.setWebsite("random.com");
   a.setLanguage("English");
   Response res=given().queryParam("key", "qaclick123").body(a).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response();   String resp= res.asString();
   System.out.println(resp);

    }
}

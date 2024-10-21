package TestClasses;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RequestAndResponseSpec {
    @Test
    public static void SerializeRequest(){
        RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
        ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
       // RestAssured.baseURI="https://rahulshettyacademy.com";
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
        Response res=given().spec(req).when().body(a).post("/maps/api/place/add/json").then().spec(resp).extract().response();
        String respo= res.asString();
        System.out.println(respo);

}}

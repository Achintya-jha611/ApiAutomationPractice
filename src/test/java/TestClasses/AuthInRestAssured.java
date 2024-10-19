package TestClasses;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class AuthInRestAssured {
@Test
    public static void Authorize(){
    RestAssured.baseURI="https://rahulshettyacademy.com";
    String response=given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type","client_credentials").formParam("scope", "trust").when().post("/oauthapi/oauth2/resourceOwner/token").then().log().all().statusCode(200).extract().response().asString();
    //System.out.println(response);
    JsonPath jp=new JsonPath(response);
    String token=jp.getString("access_token");
    //System.out.println(token);
    //How to send form param,,, when form param is of type file send it as multiPart,else in the headers only use form param funtion and pass key and value as string...
    String validate=given().queryParams("access_token",token).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().log().all().extract().response().asString();
    System.out.println(validate);


}

    @Test
    public static void AuthorizeUsingPojo(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type","client_credentials").formParam("scope", "trust").when().post("/oauthapi/oauth2/resourceOwner/token").then().log().all().statusCode(200).extract().response().asString();
        //System.out.println(response);
        JsonPath jp=new JsonPath(response);
        String token=jp.getString("access_token");
        GetCoursePojo gp=given().queryParams("access_token",token).when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().log().all().extract().response().as(GetCoursePojo.class);//using pojo classes to store response
        System.out.println(gp.getInstructor());// using pojo class methods to print values
        System.out.println(gp.getServices());
        System.out.println(gp.getExpertise());
        System.out.println(gp.getCourses());
        System.out.println(gp.getLinkedIn());



    }

}
/*

Authorization Server EndPoint:

https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token



HTTP Method : POST



Form parameters :



client_id:

        692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com

client_secret:  erZOWM9g3UtwNRj340YYaK_W

grant_type:   client_credentials

scope:  trust

******************************************************************



GetCourseDetails EndPoint (Secured by OAuth) :

https://rahulshettyacademy.com/oauthapi/getCourseDetails



HTTP Method : GET

Query Parameter : access_token

*/
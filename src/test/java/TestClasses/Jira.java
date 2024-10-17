package TestClasses;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Jira {

    @Test public static void createBug() throws IOException {
        //Creating issue
        RestAssured.baseURI="https://jhaachintya16.atlassian.net";
        String resp=given().header("authorization","Basic").header("Content-Type","application/json").header("Accept","application/json").body(new String(Files.readAllBytes(Paths.get("/Users/achintyakaushaljha/Desktop/Automation/ApiAutomationPractice/src/test/java/jsonFile/BugCreate.json")))).when().post("/rest/api/3/issue").then().assertThat().statusCode(201).extract().response().asString();
        JsonPath js=new JsonPath(resp);
        String id=js.getString("id");
        System.out.println("BUG ID"+id);


        //storing issue id,then making a call to add attachment to the issue
        String AddAttachmentResp=given().header("Authorization","Basic").header("X-Atlassian-Token","no-check").pathParam("bugid",id).multiPart("file",new File("/Users/achintyakaushaljha/Downloads/download (2).jpeg")).log().all().when().post("/rest/api/3/issue/{bugid}/attachments").then().statusCode(200).extract().response().asString();//this is a better way to pass bug id dynamically in enpoint
        System.out.println(AddAttachmentResp);
    }
    @Test public static void createBug2() throws IOException {
        //Creating issue
        RestAssured.baseURI="https://jhaachintya16.atlassian.net";
        String resp=given().header("authorization","Basic").header("Content-Type","application/json").header("Accept","application/json").body(new String(Files.readAllBytes(Paths.get("/Users/achintyakaushaljha/Desktop/Automation/ApiAutomationPractice/src/test/java/jsonFile/BugCreate.json")))).when().post("/rest/api/3/issue").then().assertThat().statusCode(201).extract().response().asString();
        JsonPath js=new JsonPath(resp);
        String id=js.getString("id");
        System.out.println("BUG ID"+id);


        //storing issue id,then making a call to add attachment to the issue
        String AddAttachmentResp=given().header("Authorization","Basic ").header("X-Atlassian-Token","no-check").multiPart("file",new File("/Users/achintyakaushaljha/Downloads/download (2).jpeg")).when().post("/rest/api/3/issue/"+id+"/attachments").then().statusCode(200).extract().response().asString();//concatenating the bug id directly in api endpoint
        System.out.println(AddAttachmentResp);

        String bugDetails=given().header("Accept","application/json").header("Authorization","basic").when().get("/rest/api/2/issue/"+id+"").then().log().all().statusCode(200).extract().response().asString();
        JsonPath Bug=new JsonPath(bugDetails);
        String bugTitle= Bug.getString("fields.summary");
        System.out.println("Bug summary is "+bugTitle);
    }
}

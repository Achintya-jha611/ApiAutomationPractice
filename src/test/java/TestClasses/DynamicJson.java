package TestClasses;

import JsonFiles.payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class DynamicJson {
    @Test (dataProvider="Test data")// it will fetch data from data provider object having this name
    public static void addBook(String name,String isbn,String aisle,String author)// we need to pass arguments in the test method itself in case of data provider
    {
        RestAssured.baseURI="http://216.10.245.166";
        String resp=given().header("content-type","application/json").body(payloads.AddBook(name,isbn,aisle,author)).when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();// simply mention the arguments name passed in test script method definition...values correspomnding to them are fetched by data provider and will be used
        JsonPath jp=new JsonPath(resp);
        String id=jp.get("ID");
        System.out.println(id);//in case data provider array has 3 values,but request payload has 4 keys,request will fail...hence need to send as many items in each array in payload as many are there in the payload.or else send for specific keys through DP and rest manually through json or payload call
    }

    @DataProvider(name="Test data")
    public Object[][] testData(){
     return new Object[][] {{"test1","testing1","121","abc"},{"test2","testing2","122","abc"},{"test3","testing3","123","abc"}};//Data provider is created,it contains collection of arrays,each array has collection of values required in an api payload....The number of arrays we have in data provider,that many number fo times the test script will run
    }


   @Test public static void readingJsonFileForPayload() throws IOException {
       RestAssured.baseURI="http://216.10.245.166";
       String resp= given().header("content-type","application/json").body(new String(Files.readAllBytes(Paths.get("/Users/achintyakaushaljha/Desktop/Automation/ApiAutomationPractice/src/test/java/jsonFile/jsonBody.json")))).when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
       JsonPath jp1=new JsonPath(resp);
       System.out.println(jp1.getString("ID"));

   }
}

package TestClasses;

import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class infraApis {
    public static void main(String args[])
    {
        baseURI="https://qa.inframarket.cloud";
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRob3JpemVkIjoidHJ1ZSIsImV4cCI6MTczOTQzODM1MywibG9naW5faWQiOiI5NDkxMzQ5Nzc3IiwibG9naW5fdHlwZSI6InBob25lbm8iLCJyZXF1ZXN0X3VzZXJfaWQiOjMsInRlbmFudF9pZCI6IjM0NDRjYmM0LTAxMDQtNTliNS1iNTkxLTRmZTM5NjQ2Y2I1MSJ9.tr7umW6GtM3RT9DUiffYHzJTNJcJmLW_0U-s1Z7jgw4";
        String response= given().header("authorization", token).when().get("/riskradar/aggr/account/application/CRED2797/v2").then().extract().response().asString();

        JsonPath jp=new JsonPath(response);
        String param=jp.getString("data.approved_credit");
        //System.out.println("Approved credit:->"+param);
        Map<String, Object> jsonResponse = jp.getMap("$");

        // Use a recursive method to iterate through all keys and values
        traverseJson(jsonResponse);
        //how to iterate a json response and print values for all fields?

    }
    //iterate over response object and print each key and value corresponding to eat
    public static void traverseJson(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                // If the value is another Map (nested JSON), recursively call traverseJson
                System.out.println("Key: " + key + " (nested)");
                traverseJson((Map<String, Object>) value);
            } else if (value instanceof List) {
                // If the value is a List (array), iterate through it
                System.out.println("Key: " + key + " (array)");
                List<Object> list = (List<Object>) value;
                for (int i = 0; i < list.size(); i++) {
                    Object listItem = list.get(i);
                    if (listItem instanceof Map) {
                        // If list item is a Map, traverse it as well
                        System.out.println("Item " + i + " (nested):");
                        traverseJson((Map<String, Object>) listItem);
                    } else {
                        System.out.println("Item " + i + ": " + listItem);
                    }
                }
            } else {
                // Otherwise, print the key-value pair
                System.out.println(key + ", Value: " + value);
            }
        }}
    }

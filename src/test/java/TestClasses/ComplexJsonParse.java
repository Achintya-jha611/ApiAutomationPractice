package TestClasses;

import JsonFiles.AddPlaces;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String args[]) {
        JsonPath JsonResp = new JsonPath(AddPlaces.Course());
        int numberOfCourses = JsonResp.getInt("courses.size()");
        System.out.println(numberOfCourses);// Fetching count of items in array in json body.suppose for a  key we are storing values in array,then to print size of that array
        int purchasePrice=JsonResp.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Price:->"+purchasePrice);
        int sellPrice=0;
        for(int i=0;i<numberOfCourses;i++){
            String Title=JsonResp.get("courses["+i+"].title");//Printing title for each course,basically traversing json array and printing a particular key value for all items in array
            // here i is an integer and courses is a string to insert an variable between a string this is what we need to do...close the quotes then a concat operator then var name then concat operator and again start the quotes
            sellPrice=sellPrice+ JsonResp.getInt("courses["+i+"].price")*JsonResp.getInt("courses["+i+"].copies");
            if(Title.equals("RPA")){
                System.out.println("Course Name:-> "+ Title);
                System.out.println("Copies "+JsonResp.get("courses["+i+"].copies").toString());//conditionals
            }
            //System.out.println(Title);
        }
        if(sellPrice==purchasePrice){
            System.out.println("Equal");
        }
        else{
            System.out.println("UnEqual");
        }
//Instead of executing the main method,we can also use @Test annotation of test ng and place our code inside the test function in that case main is not required for execution and code will still be executed as expected

    }
}

package TestClasses;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceApiAutomation {
    public static void main(String args[]){
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();//Create request specification object
        ResponseSpecification resp=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();// Create response specification object
        LoginPojo lp=new LoginPojo();//Creating pojo class object for sending request payload
        lp.setUserEmail("jha@gmail.com");
        lp.setUserPassword("@Test123");
        LoginResponsePojo lr=given().spec(req).body(lp).when().post("/api/ecom/auth/login").then().spec(resp).extract().response().as(LoginResponsePojo.class);//Creating pojo class object for storing response payload
       /* System.out.println(lr.getToken());
        System.out.println(lr.getUserId());
        System.out.println(lr.getMessage());*/
        String auth=lr.getToken();
        String user=lr.getUserId();
        RequestSpecification reqToCreateProduct=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",auth).build();//Create product specification object
        ResponseSpecification respForCreateProduct=new ResponseSpecBuilder().expectStatusCode(201).expectContentType(ContentType.JSON).build();// Create response specification object
        CreateProductResponsePojo cp= given().spec(reqToCreateProduct).param("productName","qwerty").param("productAddedBy",user).param("productCategory","fashion").param("productSubCategory","shirts").param("productPrice","12333").param("productDescription","Addias Originals").param("productFor","women").multiPart("productImage",new File("/Users/achintyakaushaljha/Downloads/download.png")).post("/api/ecom/product/add-product").then().spec(respForCreateProduct).extract().response().as(CreateProductResponsePojo.class);//How to send form params in body
        String productId=cp.getProductId();
        System.out.println(cp.getProductId());

       RequestSpecification reqToCreateOrder=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",auth).setContentType(ContentType.JSON).build();//Create product specification object
        OrderChildPojo orderDetails=new OrderChildPojo();
        orderDetails.setProductOrderedId(productId);
        orderDetails.setCountry("India");
        OrderChildPojo orderDetails1=new OrderChildPojo();
        orderDetails1.setProductOrderedId(productId);
        orderDetails1.setCountry("Russia");

        List<OrderChildPojo> OrderChildList= new ArrayList<OrderChildPojo>();
        OrderChildList.add(orderDetails);
        OrderChildList.add(orderDetails1);
        CreateOrderPojo Order=new CreateOrderPojo();
        Order.setOrders(OrderChildList);
        String OrderCreate=given().spec(reqToCreateOrder).body(Order).when().post("/api/ecom/order/create-order").then().statusCode(201).extract().response().asString();
        System.out.println(OrderCreate);













    }
}

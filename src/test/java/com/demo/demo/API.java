package com.demo.demo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class API {

	public static void main (String[] args)
	{
		RequestSpecification request= RestAssured.given();
		Map<String,String> header1=new HashMap<String, String>();
		Map<String,String> header2=new HashMap<String, String>();
		Map<String,String> header3=new HashMap<String, String>();
		JSONObject json1=new JSONObject();
		
		
		header1.put("Content-Type", "application/json");
		json1.put("username", "admin");
		json1.put("password", "password123");
		
		
		Response response= request.headers(header1).body(json1.toString()).post("https://restful-booker.herokuapp.com/auth");
		JSONObject json2 =new JSONObject(response.asString());
		String token= json2.getString("token");
		System.out.println(token);
		
		JSONObject json3=new JSONObject();
		JSONObject json4=new JSONObject();
		
		json3.put("firstname", "Jim");
		json3.put("lastname", "Brown");
		json3.put("totalprice", 111);
		json3.put("depositpaid", true);
		json3.put("additionalneeds", "Breakfast");
		
		json4.put("checkin", "2018-01-01");
		json4.put("checkout", "2019-01-01");
		
		json3.put("bookingdates", json4);
		
		header2.put("Content-Type", "application/json");
		header2.put("Accept", "application/json");
		
		response= request.headers(header2).body(json3.toString()).post("https://restful-booker.herokuapp.com/booking");
		
		JSONObject bookingObject =new JSONObject(response.asString());
		String id= String.valueOf(bookingObject.getInt("bookingid"));
		
		header3.put("Accept", "application/json");
		response= request.headers(header3).get("https://restful-booker.herokuapp.com/booking/"+id);
		
		JSONObject fetchbookingObject =new JSONObject(response.asString());
		JSONObject bookingdetails =fetchbookingObject.getJSONObject("bookingdates");
		String checkin=bookingdetails.getString("checkin");
		String checkout=bookingdetails.getString("checkout");
		System.out.println("checkin :" +checkin);
		System.out.println("checkout :" +checkout);
		
		
		
	}
}

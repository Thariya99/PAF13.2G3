package com;
import model.Users;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/users") 
public class UsersService {
	
	Users userOBJ = new Users();
	
	 @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readItems() 
	  { 
	  return userOBJ.readItems(); 
	  }
	 
	
	 @POST
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String insertItem(@FormParam("userName") String userName, 
	  @FormParam("userEmail") String userEmail, 
	  @FormParam("userPassword") String userPassword, 
	  @FormParam("userPhoneNo") String userPhoneNo) 
	 { 
	  String output = userOBJ.insertItem( userName, userEmail, userPassword,userPhoneNo); 
	 return output; 
	 }
	 
	 
	 @PUT
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_JSON) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String updateItem(String itemData) 
	 { 
	 //Convert the input string to a JSON object 
	  JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	 //Read the values from the JSON object
	  String userID = itemObject.get("userID").getAsString(); 
	  String userName = itemObject.get("userName").getAsString(); 
	  String userEmail = itemObject.get("userEmail").getAsString(); 
	  String userPassword = itemObject.get("userPassword").getAsString(); 
	  String userPhoneNo = itemObject.get("userPhoneNo").getAsString(); 
	  String output = userOBJ.updateItem(userID, userName, userEmail,userPassword, userPhoneNo); 
	 return output; 
	 }
	 
	 
	 @DELETE
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_XML) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String deleteItem(String itemData) 
	 { 
	 //Convert the input string to an XML document
	  Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	  
	 //Read the value from the element <itemID>
	  String userID = doc.select("projectID").text(); 
	  String output = userOBJ.deleteItem(userID); 
	  return output; 
	 }

}

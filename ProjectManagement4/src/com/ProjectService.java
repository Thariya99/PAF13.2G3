package com;
import model.Projects;

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

@Path("/Projects") 
public class ProjectService {
	
	Projects projectOBJ = new Projects();
	
	 @GET
	 @Path("/") 
	 @Produces(MediaType.TEXT_HTML) 
	 public String readItems() 
	  { 
	  return projectOBJ.readItems(); 
	  }
	 
	
	 @POST
	 @Path("/") 
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	 @Produces(MediaType.TEXT_PLAIN) 
	 public String insertItem(@FormParam("projectCode") String itemCode, 
	  @FormParam("projectName") String itemName, 
	  @FormParam("projectPrice") String itemPrice, 
	  @FormParam("projectDesc") String itemDesc) 
	 { 
	  String output = projectOBJ.insertItem(itemCode, itemName, itemPrice, itemDesc); 
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
	  String itemID = itemObject.get("projectID").getAsString(); 
	  String itemCode = itemObject.get("projectCode").getAsString(); 
	  String itemName = itemObject.get("projectName").getAsString(); 
	  String itemPrice = itemObject.get("projectPrice").getAsString(); 
	  String itemDesc = itemObject.get("projectDesc").getAsString(); 
	  String output = projectOBJ.updateItem(itemID, itemCode, itemName, itemPrice, itemDesc); 
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
	  String itemID = doc.select("projectID").text(); 
	  String output = projectOBJ.deleteItem(itemID); 
	  return output; 
	 }

}

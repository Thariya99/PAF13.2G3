package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Projects {
	// conecting to database
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
	 } 
	 catch (Exception e) 
	 {
		 e.printStackTrace();
	 } 
	 return con; 
	 } 
	
	
	//inserting method
	public String insertItem(String code, String name, String price, String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // query
	 String query = " insert into projects (`projectID`,`projectCode`,`projectName`,`projectPrice`,`projectDesc`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, code); 
	 preparedStmt.setString(3, name); 
	 preparedStmt.setDouble(4, Double.parseDouble(price)); 
	 preparedStmt.setString(5, desc); 
	// execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	
	
	// Retrieve method
	
	public String readItems() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for reading."; 
		 } 
	 // table creation
	 output = "<table border='1'><tr><th>Project Code</th><th>Project Name</th>" +
	 "<th>Project Price</th>" + 
	 "<th>Project Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 //query
	 String query = "select * from projects"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String itemID = Integer.toString(rs.getInt("projectID")); 
	 String itemCode = rs.getString("projectCode"); 
	 String itemName = rs.getString("projectName"); 
	 String itemPrice = Double.toString(rs.getDouble("projectPrice")); 
	 String itemDesc = rs.getString("projectDesc"); 
	 // Add into the  table
	 output += "<tr><td>" + itemCode + "</td>"; 
	 output += "<td>" + itemName + "</td>"; 
	 output += "<td>" + itemPrice + "</td>"; 
	 output += "<td>" + itemDesc + "</td>"; 
	 
	 // update and delete buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
			 + "class='btnUpdate btn btn-secondary' data-itemid='" + itemID + "'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' "
			 + "class='btnRemove btn btn-danger' data-itemid='" + itemID + "'></td></tr>";  
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	public String updateItem(String ID, String code, String name, String price, String desc)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {
			 return "Error while connecting to the database for updating."; 
		 } 
		 // query
		 String query = "UPDATE projects SET projectCode=?,projectName=?,projectPrice=?,projectDesc=? WHERE projectID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // values
		 preparedStmt.setString(1, code); 
		 preparedStmt.setString(2, name); 
		 preparedStmt.setDouble(3, Double.parseDouble(price)); 
		 preparedStmt.setString(4, desc); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 // executing the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newItems = readItems(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	public String deleteItem(String itemID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // query
	 String query = "delete from projects where projectID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 //  values
	 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
	 // executing the statement
	 preparedStmt.execute(); 
	 con.close();
	 
	 String newItems = readItems(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
	 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
}

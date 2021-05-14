package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Users {
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
	 {e.printStackTrace();} 
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
	 String query = " insert into usersnew (`userId`,`userName`,`userEmail`,`userPassword`,`userPhoneNo`)"
	 + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, code); 
	 preparedStmt.setString(3,name); 
	 preparedStmt.setString(4,price); 
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
	 output = "<table border='1'><tr><th>User Name</th><th>User Email</th>" +
	 "<th>User Password</th>" + 
	 "<th>User PhoneNo</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 //query
	 String query = "select * from usersnew"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String itemID = Integer.toString(rs.getInt("userId")); 
	 String itemCode = rs.getString("userName"); 
	 String itemName = rs.getString("userEmail"); 
	 String itemPrice = rs.getString("userPassword"); 
	 String itemDesc = rs.getString("userPhoneNo"); 
	 // Add into the  table
	 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + itemID + "'>"
			 + itemCode + "</td>"; 
	 output += "<td>" + itemName + "</td>"; 
	 output += "<td>" + itemPrice + "</td>"; 
	 output += "<td>" + itemDesc + "</td>"; 
	 
	 // update and delete buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
	 + "<td><form method='post' action='users.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	 + "<input name='hidItemIDDelete' type='hidden' value='" + itemID 
	 + "'>" + "</form></td></tr>"; 
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
	
	
	
	
	public String updateItem(String ID,  String name,String email, String password, String phoneno)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // query
		 String query = "UPDATE usersnew SET userName=?,userEmail=?,userPassword=?,userPhoneNo=? WHERE userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, email); 
		 preparedStmt.setString(3, password); 
		 preparedStmt.setString(4, phoneno); 
		 preparedStmt.setInt(5, Integer.parseInt(ID)); 
		 // executing the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	
	public String deleteItem(String userID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // query
	 String query = "delete from usersnew where userId=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 //  values
	 preparedStmt.setInt(1, Integer.parseInt(userID)); 
	 // executing the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
}

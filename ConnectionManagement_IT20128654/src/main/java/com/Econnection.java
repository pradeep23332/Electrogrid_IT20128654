package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Econnection
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid(eg)_db", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertEconnection(String username, String address, String maintown, String postalcode, String postnumber){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into connection_management(`userName`,`address`,`mainTown`,`postalCode`,`postNumber`)"+" values ( ?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding value
						preparedStmt.setString(1, username); 
						preparedStmt.setString(2, address); 
						preparedStmt.setString(3, maintown);
						preparedStmt.setString(4, postalcode); 
						preparedStmt.setString(5, postnumber); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newEconnections = readEconnections(); 
						output = "{\"status\":\"success\",\"data\":\""+newEconnections+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the econnection.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readEconnections() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr>"
		 		+ "<th>Name</th><th>Address</th>"
		 		+ "<th>Main Town</th>"
		 		+ "<th>Postal Code</th>"
		 		+ "<th>Post Number</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from connection_management"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
			 String connectionNo = Integer.toString(rs.getInt("connectionNo")); 
				String userName = rs.getString("userName"); 
				String address = rs.getString("address"); 
				String mainTown = rs.getString("mainTown");
				String postalCode = rs.getString("postalCode");
				String postNumber = rs.getString("postNumber");
		 // Add into the html table
		 output += "<tr><td><input id='hidEconnectionIDUpdate' name='hidEconnectionIDUpdate' type='hidden' value='"+connectionNo+"'>"+userName+"</td>"; 
		 output += "<td>" + address + "</td>"; 
		 output += "<td>" + mainTown + "</td>"; 
		 output += "<td>" + postalCode + "</td>"; 
		 output += "<td>" + postNumber + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-econnectionid='" + connectionNo+ "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-econnectionid='" + connectionNo + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the econnections."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
		public String updateEconnection(String connectionno, String username, String address, String maintown, String postalcode, String postnumber){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE connection_management SET userName=?,address=?,mainTown=?,postalCode=?,postNumber=? WHERE connectionNo=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, username); 
							preparedStmt.setString(2, address); 
							preparedStmt.setString(3, maintown);
							preparedStmt.setString(4, postalcode); 
							preparedStmt.setString(5, postnumber); 
							preparedStmt.setInt(6, Integer.parseInt(connectionno) ); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
						
							String newEconnections = readEconnections(); 
							output = "{\"status\":\"success\",\"data\":\""+newEconnections+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the connection.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
		public String deleteEconnection(String connectionNo){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from connection_management where connectionNo=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(connectionNo)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newEconnections = readEconnections(); 
						output = "{\"status\":\"success\",\"data\":\""+newEconnections+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the item.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			}


		
			
			
			
			
} 

			
			
			
			


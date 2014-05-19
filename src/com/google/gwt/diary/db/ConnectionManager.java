package com.google.gwt.diary.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
  
	private String className = "oracle.jdbc.OracleDriver";
	private String userName = "system";
	private String password = "8560477Oracle";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static ConnectionManager connectionInstance = null;
  
  public ConnectionManager(){
	  
  }
  
  public static synchronized ConnectionManager getInstance() {
	if(connectionInstance == null) {
	  connectionInstance = new ConnectionManager(); 		
	}
	return connectionInstance;
  }
  
  public Connection getConnection(){
	  
	  Connection conn = null;
	  try {
		  Class.forName(className);
		  conn = DriverManager.getConnection (url, userName, password);
		  System.out.println("Connection Established");
	  }  catch (ClassNotFoundException e) {
		  e.printStackTrace();
	  }	 catch (SQLException e) {
		  System.out.println("SQL Exception");
		  e.printStackTrace();
	  }
	  return conn;
  }

  
  public void closeConnection(Connection conn){
	  try {
		  conn.close();
	  } catch (SQLException e) {
		  e.printStackTrace();
	  }
  } 
}

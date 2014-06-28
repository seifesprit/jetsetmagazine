package tn.edu.esprit.info.jetsetmagasine.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	private static Connection myConnetion;
	private static String db_name = "jetsetmagasine";
	private static String url="jdbc:mysql://localhost:3306/"+db_name+"?allowMultiQueries=true";
	private static String user = "root";
	private static String password = "";

	public static Connection giveMyconnection() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver chargé");
			
			myConnetion = DriverManager.getConnection(url, user, password);
						
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return myConnetion;
	}
	
}

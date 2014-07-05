package tn.edu.esprit.info.jetsetmagasine.services.business.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import tn.edu.esprit.info.jetsetmagasine.services.business.interfaces.IActuality;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class ActualityBusiness implements IActuality {

	private static ActualityBusiness intancesof;
	
	public ActualityBusiness() {
		// TODO Auto-generated constructor stub
	}
	
	public static ActualityBusiness getIntanceof(){
		if(intancesof == null) intancesof = new ActualityBusiness();
		return intancesof;
	}
	
	@Override
	public void changeValide(int id, boolean value) {
		
		Connection connection = DataBaseConnection.giveMyconnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "update actuality set valide = "+value+" where id_auto = "+id+" ";

			statement.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}

package tn.edu.esprit.info.jetsetmagasine.services.business.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.CategoryDao;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class LeaderBusiness {
	
	public static LeaderBusiness intancesof;
	
	public LeaderBusiness() {
		// TODO Auto-generated constructor stub
	}
	
	public static LeaderBusiness getIntanceof(){
		if(intancesof == null) intancesof = new LeaderBusiness();
		return intancesof;
		
	}
	
	public Leader verifLoginPassword(String login , String password){
		
		Connection connection = DataBaseConnection.giveMyconnection();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from user where login = '"+login+"' and password = '"+password+"'";
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.first()){
				Leader leader = new Leader(resultSet.getInt("id_auto"),
						resultSet.getString("login"),
						resultSet.getString("password"),
						resultSet.getString("nom_prenom"),
						resultSet.getString("email"), CategoryDao.getInstanceof().findById(resultSet.getInt("id_category")));
	
				return leader;
			}
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

		return null;
		
		
	}

}

package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.User;
import tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces.IDaoGenerique;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class UserDao implements IDaoGenerique<User> {

	public static UserDao intanceof;
	
	private UserDao() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean add(User user) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "insert into user (nom_prenom,login,password,email)values("
					+ "'"+ user.getNom_prenom()+ "',"
					+ "'"+ user.getLogin() + "',"
					+ "'"+user.getPassword()+"',"
					+ "'"+user.getEmail()+"')";

			statement.executeUpdate(sql);
			return true;
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
		
		return false;
	}

	@Override
	public boolean update(User object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(User object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static UserDao getInstanceof(){
		
		if(intanceof == null){
			intanceof = new UserDao();	
		}
		return intanceof;
		
	}
}

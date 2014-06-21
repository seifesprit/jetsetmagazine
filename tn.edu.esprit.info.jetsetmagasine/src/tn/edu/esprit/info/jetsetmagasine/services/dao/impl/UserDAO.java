package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.User;
import tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces.IDaoGenerique;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class UserDAO implements IDaoGenerique<User, Integer> {

	public void utilisateurAdd(User utilisateur) {

		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "insert into user (nom_prenom,login,password,email)values("
					+ "'"+ utilisateur.getNom_prenom()+ "',"
					+ "'"+ utilisateur.getLogin() + "',"
					+ "'"+utilisateur.getPassword()+"',"
					+ "'"+utilisateur.getEmail()+"')";

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

	@Override
	public boolean add(User object) {
		// TODO Auto-generated method stub
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
	public List<User> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Integer integer) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

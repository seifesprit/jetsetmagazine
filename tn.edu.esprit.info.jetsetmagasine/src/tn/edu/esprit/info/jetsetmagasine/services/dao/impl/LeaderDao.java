package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.Category;
import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces.IDaoGenerique;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class LeaderDao implements IDaoGenerique<Leader> {

	private static LeaderDao intanceof;

	private LeaderDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Leader user) {
		
		int id_category = 0;
		if (user.getCategory() == null) {
			id_category = 0;
		} else {
			
			id_category = user.getCategory().getId_auto();
		}
		
		Connection connection = DataBaseConnection.giveMyconnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "insert into user (nom_prenom,login,password,email,type,id_category)values("
					+ "'"
					+ user.getNom_prenom()
					+ "',"
					+ "'"
					+ user.getLogin()
					+ "',"
					+ "'"
					+ user.getPassword()
					+ "',"
					+ "'"
					+ user.getEmail() + "'," + "'leader'," + id_category + ")";

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
	public boolean update(Leader user) {
		Connection connection = DataBaseConnection.giveMyconnection();

		int id_category = 0;
		if (user.getCategory() == null) {
			id_category = 0;
		} else {
			id_category = user.getCategory().getId_auto();
		}

		try {
			Statement statement = connection.createStatement();
			String sql = "update user set  nom_prenom ='"
					+ user.getNom_prenom() + "'," + "login = '"
					+ user.getLogin() + "'," + "password='"
					+ user.getPassword() + "'," + "email='" + user.getEmail()
					+ "'," + "id_category=" + id_category + ""
					+ " where id_auto = " + user.getId_auto() + "";
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
	public boolean remove(Leader user) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "delete from user where id_auto = "
					+ user.getId_auto();
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
	public List<Leader> findAll() {
		Connection connection = DataBaseConnection.giveMyconnection();
		List<Leader> leaders = new ArrayList<Leader>();
		
		try {

			Statement statement = connection.createStatement();
			String sql = "select * from user where type = 'leader'";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Leader leader = new Leader(resultSet.getInt("id_auto"),
						resultSet.getString("login"),
						resultSet.getString("password"),
						resultSet.getString("nom_prenom"),
						resultSet.getString("email"), CategoryDao.getInstanceof().findById(resultSet.getInt("id_category")) );
				leaders.add(leader);
			}
			return leaders;
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

	@Override
	public Leader findById(Integer id) {

		Connection connection = DataBaseConnection.giveMyconnection();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from user where id_auto=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.first();
			Leader leader = new Leader(resultSet.getInt("id_auto"),
					resultSet.getString("login"),
					resultSet.getString("password"),
					resultSet.getString("nom_prenom"),
					resultSet.getString("email"), null);

			return leader;
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

	public static LeaderDao getInstanceof() {

		if (intanceof == null) {
			intanceof = new LeaderDao();
		}
		return intanceof;

	}
}

package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces.IDaoGenerique;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class ActualityDao implements IDaoGenerique<Actuality> {

	private static ActualityDao instancesof;

	private ActualityDao() {

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Actuality act) {
		// TODO Auto-generated method stub
		Connection connection = DataBaseConnection.giveMyconnection();
		try {

			java.sql.Date date_ajout_DB = new java.sql.Date(act.getDate_ajout()
					.getTime());

			java.sql.Date date_redaction_DB = new java.sql.Date(act
					.getDate_redaction().getTime());

			String sql = "INSERT INTO actuality (titre,id_category,description,date_ajout,date_redaction,source,type,valide,image) "
										+"values (?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement  statement = connection.prepareStatement(sql);
			
			statement.setString(1, act.getTitre());
			statement.setInt(2, act.getCategory().getId_auto());
			statement.setString(3, act.getDescription());
			statement.setDate(4, date_ajout_DB);
			statement.setDate(5, date_redaction_DB);
			statement.setString(6, act.getSource());
			statement.setString(7, act.getType());
			statement.setBoolean(8, act.isValide());
			statement.setString(9, act.getImage());
			
			statement.executeUpdate();
			
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
	public boolean update(Actuality act) {
		// TODO Auto-generated method stub
		Connection connection = DataBaseConnection.giveMyconnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "update actuality set (titre,id_category,description,date_ajout,date_redaction,source,type,valide,image) values("
					+ "'"
					+ act.getTitre()
					+ "',"
					+ "'"
					+ act.getCategory()
					+ "',"
					+ "'"
					+ act.getDescription()
					+ "',"
					+ "'"
					+ act.getDate_ajout()
					+ "',"
					+ "'"
					+ act.getDate_redaction()
					+ "',"
					+ "'"
					+ act.getSource()
					+ "',"
					+ "'"
					+ act.getType()
					+ "',"
					+ "'"
					+ act.isValide()
					+ "'," + "'" + act.getImage() + "')";

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
	public boolean remove(Actuality act) {
		// TODO Auto-generated method stub
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "delete from actuality where id_auto = "
					+ act.getId_auto();
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
	public List<Actuality> findAll() {
		Connection connection = DataBaseConnection.giveMyconnection();
		List<Actuality> acts = new ArrayList<Actuality>();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from actuality";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Actuality act = new Actuality(resultSet.getInt("id_auto"),
						resultSet.getString("titre"),
						resultSet.getString("description"),
						resultSet.getString("type"),
						resultSet.getDate("date_ajout"),
						resultSet.getDate("date_redaction"),
						resultSet.getBoolean("valide"),
						resultSet.getString("image"), LeaderDao.getInstanceof()
								.findById(resultSet.getInt("id_leader")),
						CategoryDao.getInstanceof().findById(
								resultSet.getInt("id_category")),
						resultSet.getString("source"));
				acts.add(act);
			}
			return acts;
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
	public Actuality findById(Integer id) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from actuality where id_auto =" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				Actuality act = new Actuality(resultSet.getInt("id_auto"),
						resultSet.getString("titre"),
						resultSet.getString("description"),
						resultSet.getString("type"),
						resultSet.getDate("date_ajout"),
						resultSet.getDate("date_redaction"),
						resultSet.getBoolean("valide"),
						resultSet.getString("image"), LeaderDao.getInstanceof()
								.findById(resultSet.getInt("id_leader")),
						CategoryDao.getInstanceof().findById(
								resultSet.getInt("id_category")),
						resultSet.getString("source"));
				return act;
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

	public static ActualityDao getInstanceof() {
		if (instancesof == null)
			instancesof = new ActualityDao();

		return instancesof;
	}

}

package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
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
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO actuality (titre,id_category,description,date_ajout,date_redaction,source,type,valide,une,image) values("
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
					+act.getDate_ajout()
					+ "',"
					+ "'"
					+act.getDate_redaction()
					+ "',"
					+ "'"
					+act.getSource()
					+ "',"
					+ "'"
					+act.getType()
					+ "',"
					+ "'"
					+act.isValide()
					+ "',"
					+ "'"
					+act.getUne()
					+ "',"
					+ "'"
				    +act.getImage() + ")";

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
		return false;
	}

	@Override
	public boolean update(Actuality act) {
		// TODO Auto-generated method stub
		Connection connection = DataBaseConnection.giveMyconnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "update actuality set (titre,id_category,description,date_ajout,date_redaction,source,type,valide,une,image) values("
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
					+act.getDate_ajout()
					+ "',"
					+ "'"
					+act.getDate_redaction()
					+ "',"
					+ "'"
					+act.getSource()
					+ "',"
					+ "'"
					+act.getType()
					+ "',"
					+ "'"
					+act.isValide()
					+ "',"
					+ "'"
					+act.getUne()
					+ "',"
					+ "'"
				    +act.getImage() + ")";

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
			String sql = "select * from actuality where type = 'leader'";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Actuality act = new Actuality(resultSet.getInt("id_auto"),
						resultSet.getString("titre"),
						resultSet.getString("description"),
						resultSet.getDate("date_ajout"),
						resultSet.getDate("date_redaction"),
						resultSet.getString("source"),
						resultSet.getString("type"),
						resultset.getBoolean("valide"),
						resultSet.getString("une"),
						resultSet.getString("image")
						, CategoryDao.getInstanceof().findById(resultSet.getInt("id_category")) );
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
			String sql = "select * from actuality where id_auto =" +id;
			ResultSet resultSet = statement.executeQuery(sql);
				Actuality act = new Actuality(resultSet.getInt("id_auto"),
						resultSet.getString("titre"),
						resultSet.getString("description"),
						resultSet.getDate("date_ajout"),
						resultSet.getDate("date_redaction"),
						resultSet.getString("source"),
						resultSet.getString("type"),
						resultset.getBoolean("valide"),
						resultSet.getString("une"),
						resultSet.getString("image")
						, CategoryDao.getInstanceof().findById(resultSet.getInt("id_category")) );
			return act;
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

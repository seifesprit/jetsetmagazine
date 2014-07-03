package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.Subscriber;
import tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces.IDaoGenerique;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class SubscriberDao implements IDaoGenerique<Subscriber> {

	private static SubscriberDao intanceof;

	private SubscriberDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Subscriber subscriber) {

		Connection connection = DataBaseConnection.giveMyconnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "insert into subscriber (nom_prenom,email,id_fb)values("
					+ "'"+ subscriber.getNom_prenom()+ "',"
					+ "'"+ subscriber.getEmail()+ "',"
					+ "'"+ subscriber.getId_fb() + "')";

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
	public boolean update(Subscriber subscriber) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "update subscriber set nom_prenom = '"
					+ subscriber.getNom_prenom() + "' , email = '"
					+ subscriber.getEmail() + "' ,id_fb= '"
					+ subscriber.getId_fb() + "' where id_auto = "
					+ subscriber.getId_auto();

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
	public boolean remove(Subscriber subscriber) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "delete from subscriber where id_auto = "
					+ subscriber.getId_auto();
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
	public List<Subscriber> findAll() {
		Connection connection = DataBaseConnection.giveMyconnection();
		List<Subscriber> subscribers = new ArrayList<Subscriber>();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from subscriber";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Subscriber subscriber = new Subscriber(
						resultSet.getInt("id_auto"),

						resultSet.getString("nom_penom"),
						resultSet.getString("email"),
						resultSet.getString("id_fb"));
				subscribers.add(subscriber);
			}
			return subscribers;
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
	public Subscriber findById(Integer id) {

		Connection connection = DataBaseConnection.giveMyconnection();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from subscriber where id_auto=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.first();
			Subscriber subscriber = new Subscriber(resultSet.getInt("id_auto"),

			resultSet.getString("nom_prenom"), resultSet.getString("email"),
					resultSet.getString("id_fb"));

			return subscriber;
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

	public static SubscriberDao getInstanceof() {

		if (intanceof == null) {
			intanceof = new SubscriberDao();
		}
		return intanceof;

	}
}

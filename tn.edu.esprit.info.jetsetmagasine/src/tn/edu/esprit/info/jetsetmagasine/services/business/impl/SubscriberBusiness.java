package tn.edu.esprit.info.jetsetmagasine.services.business.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tn.edu.esprit.info.jetsetmagasine.domain.Subscriber;
import tn.edu.esprit.info.jetsetmagasine.services.business.interfaces.ISubscriber;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class SubscriberBusiness implements ISubscriber {

	private static SubscriberBusiness instancesof;

	@Override
	public Subscriber findByIdfb(String id) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from subscriber where id_fb='" + id + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.first()) {
				Subscriber subscriber = new Subscriber(
						resultSet.getInt("id_auto"),
						resultSet.getString("nom_prenom"),
						resultSet.getString("email"),
						resultSet.getString("id_fb"),
						resultSet.getString("image"));

				return subscriber;
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

	public static SubscriberBusiness getInstanceof() {
		if (instancesof == null)
			instancesof = new SubscriberBusiness();
		return instancesof;
	}

}

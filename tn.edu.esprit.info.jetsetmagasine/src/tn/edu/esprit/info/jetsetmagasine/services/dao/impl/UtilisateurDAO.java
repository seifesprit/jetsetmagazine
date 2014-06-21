package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import tn.edu.esprit.info.jetsetmagasine.domain.Utilisateur;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class UtilisateurDAO {

	public void createTable() {

		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "drop table utilisateur; create table utilisateur("
					+ "id_auto int(11) NOT NULL AUTO_INCREMENT,"
					+ "`nom_prenom` varchar(255) NOT NULL,"
					+ " `login` varchar(255) NOT NULL,"
					+ " `password` varchar(255) NOT NULL,"
					+ "`email` varchar(255) NOT NULL,"
					+ "PRIMARY KEY (`id_auto`))";

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

	public void utilisateurAdd(Utilisateur utilisateur) {

		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "insert into utilisteur (nom_prenom,login,password,email)values("
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

}

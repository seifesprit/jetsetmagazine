package tn.edu.esprit.info.jetsetmagasine.utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ResetDataBase {

	public static String sql = "drop table user; create table user("
			+ "id_auto int(11) NOT NULL AUTO_INCREMENT,"
			+ "`nom_prenom` varchar(255) NOT NULL,"
			+ " `login` varchar(255) NOT NULL,"
			+ " `password` varchar(255) NOT NULL,"
			+ "`email` varchar(255) NOT NULL,"
			+ "PRIMARY KEY (`id_auto`))";
	
	public static void main(String[] args) {

			Connection connection = DataBaseConnection.giveMyconnection();

			try {
				Statement statement = connection.createStatement();
				

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


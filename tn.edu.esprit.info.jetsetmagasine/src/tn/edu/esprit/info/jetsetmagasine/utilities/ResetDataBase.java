package tn.edu.esprit.info.jetsetmagasine.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ResetDataBase {

	public static String sql = null;

	public static String db_definition() {

		BufferedReader ficTexte;
		String ligne = "";
		String contenu = "";
		try {
			
			ficTexte = new BufferedReader(new FileReader(new File(
					"db_definition/jetsetmagasine.sql")));
			ligne = ficTexte.readLine();
			
			while (ligne != null){
				contenu += ligne;
				ligne = ficTexte.readLine();
				//System.out.println(ligne);
			}
			
			ficTexte.close();
			
			return contenu;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String[] args) {

		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			System.out.println("debut requete");
			sql = db_definition();
			System.out.println(sql+"\n requete chargé");
			statement.executeUpdate(sql);
			System.out.println("requete executé");
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

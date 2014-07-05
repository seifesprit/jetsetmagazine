package tn.edu.esprit.info.jetsetmagasine.services.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.services.business.interfaces.IActuality;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.CategoryDao;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.LeaderDao;
import tn.edu.esprit.info.jetsetmagasine.utilities.DataBaseConnection;

public class ActualityBusiness implements IActuality {

	private static ActualityBusiness intancesof;
	
	public ActualityBusiness() {
		// TODO Auto-generated constructor stub
	}
	
	public static ActualityBusiness getIntanceof(){
		if(intancesof == null) intancesof = new ActualityBusiness();
		return intancesof;
	}
	
	@Override
	public void changeValide(int id, boolean value) {
		
		Connection connection = DataBaseConnection.giveMyconnection();
		try {
			Statement statement = connection.createStatement();
			String sql = "update actuality set valide = "+value+" where id_auto = "+id+" ";

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
	public List<Actuality> findByWordKey(String word) {
		
		Connection connection = DataBaseConnection.giveMyconnection();
		List<Actuality> acts = new ArrayList<Actuality>();
		word = "%"+word+"%";
		try {

			String sql = "select actuality.* from actuality,category where (actuality.id_category = category.id_auto) and"
					+ "  (actuality.titre like ?  or "
					+"category.libelle like ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, word);
			statement.setString(2, word);
			
			ResultSet resultSet = statement.executeQuery();
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

}

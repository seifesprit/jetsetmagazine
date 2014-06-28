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

public class CategoryDao implements IDaoGenerique<Category> {

	private static CategoryDao intanceof;
	
	private CategoryDao() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean add(Category category) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "insert into category (libelle)values('"
					+ category.getLibelle() + "')";

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
	public boolean update(Category category) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "update category set libelle = '"
					+ category.getLibelle() + "' where id_auto = "
					+ category.getId_auto();

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
	public boolean remove(Category category) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {
			Statement statement = connection.createStatement();
			String sql = "delete from category where id_auto = "
					+ category.getId_auto();
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
	public List<Category> findAll() {
		Connection connection = DataBaseConnection.giveMyconnection();
		List<Category> categories = new ArrayList<Category>();
		try {

			Statement statement = connection.createStatement();
			String sql = "select * from category";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Category category = new Category(resultSet.getInt("id_auto"),
						resultSet.getString("libelle"));
				categories.add(category);
			}
			
			return categories;
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
	public Category findById(Integer id) {
		Connection connection = DataBaseConnection.giveMyconnection();

		try {

			Statement statement = connection.createStatement();
			String sql = "select * from category where id_auto=" + id;
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.first();
			Category category = new Category(resultSet.getInt("id_auto"),
					resultSet.getString("libelle"));

			return category;
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
	
	public static CategoryDao getInstanceof() {

		if (intanceof == null) {
			intanceof = new CategoryDao();
		}
		return intanceof;

	}

}

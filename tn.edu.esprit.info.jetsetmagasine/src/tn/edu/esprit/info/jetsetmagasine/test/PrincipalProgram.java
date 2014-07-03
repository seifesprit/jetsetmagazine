package tn.edu.esprit.info.jetsetmagasine.test;

import tn.edu.esprit.info.jetsetmagasine.domain.Category;
import tn.edu.esprit.info.jetsetmagasine.domain.Leader;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.CategoryDao;
import tn.edu.esprit.info.jetsetmagasine.services.dao.impl.LeaderDao;

public class PrincipalProgram {

	public static void main(String[] args) {
		
		Category category = new Category(1,"Sports");
		Category category2 = new Category(2,"News");
		
		CategoryDao categoryDao = CategoryDao.getInstanceof();
		
		categoryDao.add(category);
		categoryDao.add(category2);
		
		Leader leader = new Leader(1, "root", "root", "Med Aymen Jendoubi", "jendoubimedaymen@yahoo.fr", category);
		
		LeaderDao leaderDao = LeaderDao.getInstanceof();
		
		leaderDao.add(leader);
		
		Leader leader2 = leaderDao.findById(1);
		
		leader2.setEmail("medaymenjendoubi@gmail.com");
		leader2.setLogin("admin");
		leader2.setNom_prenom("Siefallah Mhadhbi");
		leader2.setPassword("admin");
		leader2.setCategory(category2);
		
		//System.out.println(leader2);
		leaderDao.update(leader2);
		//leaderDao.remove(leader2);
		System.out.println(leaderDao.findAll());
		
	}

}

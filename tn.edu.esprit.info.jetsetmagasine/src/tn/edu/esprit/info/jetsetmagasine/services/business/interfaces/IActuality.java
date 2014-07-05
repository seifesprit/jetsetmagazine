package tn.edu.esprit.info.jetsetmagasine.services.business.interfaces;

import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;

public interface IActuality {

	void changeValide(int id , boolean value);
	List<Actuality> findByWordKey(String word);
	List<Actuality> findByCategory(int id);
}

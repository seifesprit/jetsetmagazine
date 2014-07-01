package tn.edu.esprit.info.jetsetmagasine.services.dao.impl;

import java.util.List;

import tn.edu.esprit.info.jetsetmagasine.domain.Actuality;
import tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces.IDaoGenerique;

public class ActualityDao implements IDaoGenerique<Actuality> {

	private static ActualityDao instancesof;

	private ActualityDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean add(Actuality object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Actuality object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Actuality object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Actuality> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actuality findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ActualityDao getInstanceof() {
		if (instancesof == null)
			instancesof = new ActualityDao();

		return instancesof;
	}

}

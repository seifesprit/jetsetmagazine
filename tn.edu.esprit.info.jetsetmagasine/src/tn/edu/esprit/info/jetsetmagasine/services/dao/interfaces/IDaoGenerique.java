package tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces;

import java.util.List;

public interface IDaoGenerique<Object,Integer> {
	
	boolean add(Object object);
	boolean update(Object object);
	boolean remove(Object object);
	List<Object> list();
	Object findById(Integer integer);
}

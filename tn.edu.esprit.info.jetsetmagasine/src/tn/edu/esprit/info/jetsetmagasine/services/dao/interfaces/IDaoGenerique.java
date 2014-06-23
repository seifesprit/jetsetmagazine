package tn.edu.esprit.info.jetsetmagasine.services.dao.interfaces;

import java.util.List;

public interface IDaoGenerique<T> {

	boolean add(T object);

	boolean update(T object);

	boolean remove(T object);

	List<T> findAll();

	T findById(Integer id);
}

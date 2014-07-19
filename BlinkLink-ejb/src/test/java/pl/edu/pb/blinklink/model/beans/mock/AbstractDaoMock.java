package pl.edu.pb.blinklink.model.beans.mock;

import java.util.Collection;
import java.util.LinkedList;

import pl.edu.pb.blinklink.model.beans.DaoInterface;

public abstract class AbstractDaoMock<T> implements DaoInterface<T> {
	
	protected LinkedList<T> dataStorage = new LinkedList<T>();
	
	@Override
	public void create(T entity) {
		dataStorage.add(entity);
	}

	@Override
	public void remove(T entity) {
		dataStorage.remove(entity);
	}

	@Override
	public void update(T entity) {
		int index = dataStorage.indexOf(entity);
		dataStorage.remove(index);
		dataStorage.add(entity);
	}

	@Override
	@SuppressWarnings(value="unchecked")
	public Collection<T> findAll() {
		return (Collection<T>)dataStorage.clone();
	}
	
}

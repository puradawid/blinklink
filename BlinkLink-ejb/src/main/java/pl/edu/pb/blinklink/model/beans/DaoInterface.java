package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;

/**
 * Base interface for DAO services.
 * @author Dawid Pura
 *
 * @param <T> type of data bean.
 */
public interface DaoInterface<T> {
	public void create(T entity);
	public void remove(T entity);
	public void update(T entity);
	public Collection<T> findAll();
}

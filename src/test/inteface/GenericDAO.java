package test.inteface;

import java.util.List;

public interface GenericDAO<E> {
public int save(E entity);
public int update(E entity);
public E queryById(Integer id);
public void delete(E entity);
public List<E> queryList();
}

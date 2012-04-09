package test.inteface.impl;

import java.util.List;

import test.inteface.GenericDAO;
import test.model.Person;

public class GenericDAOImpl implements GenericDAO<Person>{

	@Override
	public void delete(Person entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person queryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> queryList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Person entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Person entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}

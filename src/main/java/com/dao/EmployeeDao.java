package com.dao;

import java.util.List;

public interface EmployeeDao<E> {
	
	public List<E> getAll() ;

	public void deleteById(String employeeName);

	public void add(E employee);

}

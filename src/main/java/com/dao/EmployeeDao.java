package com.dao;

import java.util.List;

import com.model.HandlerResponse;

public interface EmployeeDao<E> {
	
	public List<E> getAll() ;

	public void deleteById(String employeeName);

	public HandlerResponse add(E employee);

}

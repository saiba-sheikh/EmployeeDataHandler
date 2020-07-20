package com.services;

import java.util.List;

public interface Services<Employee> {

	void display();

	void addEmployee();

	List<Employee> getAll();

	void deleteById();

}

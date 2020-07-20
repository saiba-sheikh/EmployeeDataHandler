package com.services;

import java.util.List;
import java.util.Scanner;

import com.dao.EmployeeDao;
import com.dao.EmployeeDaoImpl;
import com.model.Designation;
import com.model.Employee;

public class EmployeeService implements Services<Employee> {

	private EmployeeDao employeeDao;
	private InputService inputService;

	public EmployeeService(EmployeeDao employeeDao, InputService inputService) {
		this.employeeDao = employeeDao;
		this.inputService = new InputService(new Scanner(System.in));
	}

	public List<Employee> getAll() {
		List<Employee> employees = employeeDao.getAll();
		return employees;
	}

	public void deleteById() { 
		String employeName = inputService.getEmployeeId();
		employeeDao.deleteById(employeName);
	}

	public void display() {
		List<Employee> employees = getAll();
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%2s %10s %5s %10s", "ID", "NAME", "AGE", "Designation");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");

		
		  employees.stream().forEach(employee -> {
		  System.out.format("%2s %10s %5s %10s", employee.getEmployeeId(),
		  employee.getName(), employee.getAge(), employee.getDesignation());
		  System.out.println(); });
		 

		System.out.println("-----------------------------------------------------------------------------");

	}

	public void addEmployee() {
		String employeeId = inputService.getEmployeeId();
		String employeeName = inputService.getEmployeeName();
		int age = inputService.getAge();
		Designation designation = inputService.getDesignation();
		Employee employee = new Employee(employeeId, employeeName, designation, age);
		employeeDao.add(employee);
	}

}

package com.dao;

import java.util.List;

import com.helper.DataHandler;
import com.helper.DataHandlingFactory;
import com.model.Employee;
import com.model.Employees;
import com.model.exception.NoRecordFoundException;
import com.utils.Logger;
import com.utils.LoggerImpl;

public class EmployeeDaoImpl implements EmployeeDao<Employee> {
	static final String FIELD_ID_NAME = "employeeId";

	private DataHandler<Employees> dataHandler;
	private Logger logger = LoggerImpl.getLogger();
	public EmployeeDaoImpl(String dbType, String source) {
		this.dataHandler = DataHandlingFactory.getDataHandler(dbType, Employees.class, source);
	}

	@Override
	public List<Employee> getAll() {
		Employees employees = dataHandler.readDocument();
		return employees.getEmployees();
	}

	@Override
	public void deleteById(String employeeId) {
		try {
			dataHandler.deleteElement(FIELD_ID_NAME, employeeId);
			logger.recordDeleted("Successfully deleted record Id: "+ employeeId);
		} catch (NoRecordFoundException ex) {
			logger.exceptionEncountered("No Record found for employee ID: " + employeeId);
		}
	}

	@Override
	public void add(Employee employee) {
		dataHandler.addDataNode(employee, Employee.class);
		logger.recordAdded("Successfully added record");
	}

}

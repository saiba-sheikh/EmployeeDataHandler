package com.main;
import com.dao.EmployeeDao;
import com.dao.EmployeeDaoImpl;
import com.helper.DataHandlingFactory;
import com.helper.XmlDataHandler;
import com.model.Employees;
import com.services.EmployeeService;
import com.services.InputService;
import com.utils.ConsoleLogger;
import com.utils.ConsoleReader;
import com.utils.LoggerImpl;
import com.utils.PropertyReader;

public class Application {
	private static EmployeeService service;
	private static PropertyReader properties;

	/*
	 * The application is initialized for the required dependencies load the
	 * required properties
	 */
	public void initilizeApplication() {
		properties = PropertyReader.getInstance();
		// Register all possible type of handlers
		DataHandlingFactory.register(Employees.class, new XmlDataHandler<Employees>(properties.getProperty("db.fileName"), Employees.class));
		//Register Loggers
		LoggerImpl.registerLogger(new ConsoleLogger());
		
	}
/*
 * Returns instance of EmployeeService
 */
	public EmployeeService employeeService() {
		if (service == null) {
			String dbType = properties.getProperty("db.type");
			String fileName = properties.getProperty("db.fileName");
			EmployeeDao employeeDao = new EmployeeDaoImpl(dbType, fileName);
			InputService inputService = new InputService(ConsoleReader.getConsoleReader().reader());
			service = new EmployeeService(employeeDao, inputService);
		}
		return service;
	}
	
}

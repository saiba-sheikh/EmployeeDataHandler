package com.main;
import java.util.Scanner;

import com.model.exception.EmployeeDataHandlerException;
import com.utils.ConsoleReader;
import com.utils.Logger;
import com.utils.LoggerImpl;

public class ApplicationLauncher {

	private Application app;
	private static Logger logger = LoggerImpl.getLogger();

	public ApplicationLauncher() {
		 app = new Application();
	}
	public void startApp() {
		System.out.println("Employee Data Service");
		System.out.println("Select an option:");
		app.initilizeApplication();
		Scanner scanner = ConsoleReader.getConsoleReader().reader();
		Boolean running = Boolean.TRUE;
		try {
		while (running) {
			mainMenu();
			Integer input = scanner.nextInt();
			try {
			switch (input) {
			case 1:
				app.employeeService().addEmployee();
				break;
			case 2:
				app.employeeService().deleteById();
				break;
			case 3:
				app.employeeService().display();
				break;
			case 4:
				running = !running;
				break;
			default:
				System.out.println("Invalid Input Try again!!");
			}
			}catch(EmployeeDataHandlerException ex) {
				//handle application exceptions as per use-case
				logger.exceptionEncountered(ex.getMessage());
				System.out.println("Enter Y to continue: ");
				String userResponse = scanner.next();
				if(!userResponse.equalsIgnoreCase("Y"))
					running = false;
			}
		}
		}
		catch (Exception e) {
			System.out.println(e.getClass().descriptorString());
			logger.exceptionEncountered("Application Terminated Abruptly"+ e.getMessage());
		}
		finally {
			ConsoleReader.getConsoleReader().closeReader();
		}
		System.out.println("Exiting Application");

	}
	public static void main(String[] args) {
		ApplicationLauncher launcher = new ApplicationLauncher();
		launcher.startApp();
	}

	private static void mainMenu() {
		System.out.println("Select an option:");
		System.out.println("Press 1 to add new Employee");
		System.out.println("Press 2 to delete an entry");
		System.out.println("Press 3 to view all data");
		System.out.println("Press 4 to exit");
	}
}

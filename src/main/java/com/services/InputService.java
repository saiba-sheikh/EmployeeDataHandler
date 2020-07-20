package com.services;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.model.Designation;
import com.model.exception.ReaderException;

/*This class is responsible for getting input from the console
 * Validation is not supported in current version
 *Can include a validation layer for inputs
 */
public class InputService {

	protected Scanner scanner;
	public InputService(Scanner scanner) {
		this.scanner = scanner;
	}
	

	public String getEmployeeName() {
		System.out.println("Enter Employee Name: ");
		return scanner.next().strip(); 
	}
	
	public String getEmployeeId() {
		System.out.println("Enter Employee Id: ");
		return scanner.next().strip();
	}
	
	public Designation getDesignation() {
		System.out.println("Select Designation: ");
		System.out.println(Arrays.toString(Designation.values())); 
		return Designation.getDesignation( scanner.next().strip() );
			
	}

	public int getAge() {
		System.out.println("Enter Age: ");
		try {
			int age = scanner.nextInt();
			return age;

		}catch(InputMismatchException ex){
			throw new ReaderException("Invalid input for Age: "+ ex.getMessage());
		}
	}
}

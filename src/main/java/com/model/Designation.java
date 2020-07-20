package com.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

import com.model.exception.EmployeeDataHandlerException;

@XmlType(name = "")
@XmlEnum
public enum Designation {
	@XmlEnumValue("Developer")
	DEVELOPER, 
	@XmlEnumValue("Senior Developer")
	SENIOR_DEVELOPER, 
	@XmlEnumValue("Team Lead")
	TEAM_LEAD,
	@XmlEnumValue("Project Lead")
	PROJECT_LEAD;

	public static Designation getDesignation(String designationString) {
		try {
			return Designation.valueOf(designationString.toUpperCase()); 
		} catch (IllegalArgumentException ex) {
			throw new InvalidDesignation(designationString + " is a invalid designation");
		}
	}

	public static class InvalidDesignation extends EmployeeDataHandlerException {

		public InvalidDesignation(String message) {
			super(message);
		}

	}
}

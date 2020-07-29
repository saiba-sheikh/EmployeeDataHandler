package com.model;

import java.util.Optional;

public class HandlerResponse {

	private Boolean success;
	private Optional<String> errorMessage;
	private Optional<String> location;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Optional<String> getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(Optional<String> errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Optional<String> getLocation() {
		return location;
	}
	public void setLocation(Optional<String> location) {
		this.location = location;
	}
	
	public HandlerResponse(Boolean success, Optional<String> errorMessage, Optional<String> location) {
		this.success = success;
		this.errorMessage = errorMessage;
		this.location = location;
	}
	public HandlerResponse() {}
	public static HandlerResponse successResponse() {
		HandlerResponse response = new HandlerResponse();
		response.setSuccess(true);
		return response;
	}
	
	public static HandlerResponse failureResponse(String errorMessage, String location) {
		return new HandlerResponse(false, Optional.of(errorMessage), Optional.of(location));
		
	}
}

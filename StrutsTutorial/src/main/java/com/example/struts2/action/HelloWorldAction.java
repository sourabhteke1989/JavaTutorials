package com.example.struts2.action;

public class HelloWorldAction {
	
	private String message;
	
	public String execute(){
		setMessage("Hello World !! Sourabh here");
		return "success";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

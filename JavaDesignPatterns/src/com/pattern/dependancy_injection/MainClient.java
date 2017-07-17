package com.pattern.dependancy_injection;

public class MainClient {
	 public static void main(String[] args) {
		String msg = "Hi, I am sourabh here.";
		String email = "sourabh.teke@gmail.com";
		String phone = "9028795248";
		MessageServiceInjector serviceInjector = null;
		ConsumerApplication app = null;
		
		//Sending email
		serviceInjector = new EmailServiceInjector();
		app = serviceInjector.getConsumerApplication();
		app.proccessMessage(msg, email);
		
		//Sending SMS
		serviceInjector = new SMSServiceInjector();
		app = serviceInjector.getConsumerApplication();
		app.proccessMessage(msg, phone);
	}
}

package com.pattern.dependancy_injection;

public class ConsumerApplication {
	private MessageService service;
	
	public ConsumerApplication(MessageService service){
		this.service = service;
	}
	
	public void proccessMessage(String msg, String rec){
		this.service.sendMessage(msg, rec);
	}
}

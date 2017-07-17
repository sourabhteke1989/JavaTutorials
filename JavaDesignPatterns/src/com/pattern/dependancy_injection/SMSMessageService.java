package com.pattern.dependancy_injection;

public class SMSMessageService implements MessageService {

	@Override
	public void sendMessage(String msg, String rec) {
		// TODO Auto-generated method stub
		System.out.println("SMSMessageService sending message");
	}

}

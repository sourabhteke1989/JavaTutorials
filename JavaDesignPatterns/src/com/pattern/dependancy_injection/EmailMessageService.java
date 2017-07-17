package com.pattern.dependancy_injection;

public class EmailMessageService implements MessageService {

	@Override
	public void sendMessage(String msg, String rec) {
		// TODO Auto-generated method stub
		System.out.println("EmailMessageService sending message");
	}

}

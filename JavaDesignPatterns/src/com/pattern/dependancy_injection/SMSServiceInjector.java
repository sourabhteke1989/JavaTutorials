package com.pattern.dependancy_injection;

public class SMSServiceInjector implements MessageServiceInjector {

	@Override
	public ConsumerApplication getConsumerApplication() {
		// TODO Auto-generated method stub
		return new ConsumerApplication(new SMSMessageService());
	}

}

package com.maxlogic.tutorials.concurrency.basic_wait_notify;

public class ClassA implements Runnable{
	
	private Object lock;
	
	public ClassA(Object lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		System.out.println("Started A run method");
		boolean flag = false;
		synchronized(this.lock) {
			while(true) {
				try {
					if(flag) {
						this.lock.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				flag = true;
				System.out.println("A here!!");
				this.lock.notify();
			}
			
		}
	}
}

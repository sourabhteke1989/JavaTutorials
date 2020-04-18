package com.maxlogic.tutorials.concurrency.basic_wait_notify;

public class ClassB implements Runnable{
	
	private Object lock;
	
	public ClassB(Object lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		System.out.println("Started B run method");
		synchronized(this.lock) {
			while(true) {
				try {
					this.lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("B here!!");
				this.lock.notify();
			}
		}
	
	}
}

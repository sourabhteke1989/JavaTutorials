package com.maxlogic.tutorials.concurrency.basic_wait_notify;

public class MainClass {
	public static void main(String[] args) {
		Object lock = new Object();
		Thread t1 = new Thread(new ClassA(lock));
		Thread t2 = new Thread(new ClassB(lock));
		t2.start();
		t1.start();
	}
}

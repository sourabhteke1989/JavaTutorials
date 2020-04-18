package com.maxlogic.tutorials.java8.defaultmethods;

public interface MathInterface {
	public int sum(int a, int b);
	
	//default method providing implementation
	default int multiply(int a, int b ) {
		return a*b;
	}
	
	default double divide(int a, int b) {
		return a/b;
	}
}

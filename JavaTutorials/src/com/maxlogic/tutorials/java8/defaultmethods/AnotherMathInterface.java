package com.maxlogic.tutorials.java8.defaultmethods;

public interface AnotherMathInterface {
	public int sum(int a, int b);
	
	default double divide(int a, int b) {
		System.out.println("AnotherMathInterface.divide() called");
		return (a/b)+1;
	}
}

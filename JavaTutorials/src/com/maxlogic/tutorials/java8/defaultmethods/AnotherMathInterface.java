package com.maxlogic.tutorials.java8.defaultmethods;

public interface AnotherMathInterface extends SuperMathInterface {
	
	void anotherMathAbstractMethod();
	
	default void anotherMathDefaultMethod() {
		System.out.println("AnotherMathInterface.anotherMathDefaultMethod() called");
	}
	
	public int sum(int a, int b);
	
	default double divide(int a, int b) {
		System.out.println("AnotherMathInterface.divide() called");
		return (a/b)+1;
	}
}

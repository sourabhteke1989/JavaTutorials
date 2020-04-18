package com.maxlogic.tutorials.java8.defaultmethods;

public interface MathInterface extends SuperMathInterface {
	
	void mathAbstractMethod();
	
	default void mathDefaultMethod() {
		System.out.println("AnotherMathInterface.mathDefaultMethod() called");
	}
	
	public int sum(int a, int b);
	
	//default method providing implementation
	@Override
	default int multiply(int a, int b ) {
		System.out.println("MathInterface.multiply() called");
		return a*b;
	}
	
	default double divide(int a, int b) {
		System.out.println("MathInterface.divide() called");
		return a/b;
	}
}

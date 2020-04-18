package com.maxlogic.tutorials.java8.defaultmethods;

public interface ReverseMathInterface extends SuperMathInterface {
	
	void reverseMathAbstractMethod();
	
	default void reverseMathDefaultMethod() {
		System.out.println("reverseMathDefaultMethod.mathDefaultMethod() called");
	}
	
	@Override
	int multiply(int a, int b );
}

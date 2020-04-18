package com.maxlogic.tutorials.java8.defaultmethods;

public interface SuperMathInterface {
	
	void superAbsractMethod();
	
	default void superDefaultMethod() {
		System.out.println("SuperMathInterface.superDefaultMethod() called");
	}
	
	default int multiply(int a, int b ) {
		System.out.println("SuperMathInterface.multiply() called");
		return (a*b)+1;
	}
	
	default void anotherSuperDefaultMethod() {
		System.out.println("SuperMathInterface.anotherSuperDefaultMethod() called");
	}
}

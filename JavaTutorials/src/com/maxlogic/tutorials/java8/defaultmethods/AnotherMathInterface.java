package com.maxlogic.tutorials.java8.defaultmethods;

public interface AnotherMathInterface {
	public int sum(int a, int b);
	
	default int divide(int a, int b) {
		return (a/b)+1;
	}
}

package com.maxlogic.tutorials.java8.defaultmethods;

public class ClassMath implements MathInterface, AnotherMathInterface{
	public int sum(int a, int b) {
		return a+b;
	}
	
	//Overriding default method of interface.
	@Override
	public int multiply(int a, int b) {
		return (a*b)+1;
	}
	
	public static void main(String[] args) {
		ClassMath cl = new ClassMath();
		System.out.println("Sum:"+cl.sum(1, 2));
		System.out.println("Multiply:"+cl.multiply(3, 2));
		System.out.println("Divide:"+cl.divide(6, 2));
	}
}

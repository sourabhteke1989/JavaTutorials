package com.maxlogic.tutorials.java8.defaultmethods;

public class ClassMath implements MathInterface, AnotherMathInterface, SuperMathInterface{
	
	@Override
	public int sum(int a, int b) {
		System.out.println("ClassMath.sum() called");
		return 0;
	}
	
	//Overriding default method of interface.
	@Override
	public double divide(int a, int b) {
		System.out.println("ClassMath.divide() called");
		return AnotherMathInterface.super.divide(a, b);
	}
	
	public double classMathOwnMultiply(int a, int b) {
		System.out.println("ClassMath.classMathOwnMultiply() called");
		//Here we can only call MathInterface multiply, 
		//AnotherMathInterface dont have multiply method implementation
		return MathInterface.super.multiply(a, b);
	}

	public static void main(String[] args) {
		ClassMath cl = new ClassMath();
		System.out.println("Sum:"+cl.sum(1, 2));
		System.out.println("Multiply:"+cl.multiply(3, 2));
		System.out.println("Divide:"+cl.divide(6, 2));
		cl.mathAbstractMethod();
		cl.anotherMathAbstractMethod();
		cl.superAbsractMethod();
		cl.superDefaultMethod();
	}

	@Override
	public void superAbsractMethod() {
		System.out.println("ClassMath.superAbsractMethod() called");
	}

	@Override
	public void anotherMathAbstractMethod() {
		System.out.println("ClassMath.anotherMathAbstractMethod() called");
	}

	@Override
	public void mathAbstractMethod() {
		System.out.println("ClassMath.mathAbstractMethod() called");
	}

	
}

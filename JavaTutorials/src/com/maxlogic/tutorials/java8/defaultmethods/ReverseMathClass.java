package com.maxlogic.tutorials.java8.defaultmethods;

public class ReverseMathClass implements ReverseMathInterface, SuperMathInterface {

	@Override
	public void superAbsractMethod() {
		System.out.println("ReverseMathClass.superAbsractMethod called");
	}

	public static void main(String[] args) {
		ReverseMathClass cl = new ReverseMathClass();
		cl.multiply(2, 3);
		cl.superAbsractMethod();
		cl.superDefaultMethod();
		cl.anotherSuperDefaultMethod();
		cl.reverseMathAbstractMethod();
		cl.reverseMathDefaultMethod();
	}

	@Override
	public void reverseMathAbstractMethod() {
		System.out.println("ReverseMathClass.reverseMathAbstractMethod called");
	}

	@Override
	public int multiply(int a, int b) {
		System.out.println("ReverseMathClass.multiply called");
		return 0;
	}

}

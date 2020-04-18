package com.maxlogic.tutorials.datatypes;

public class MainClass {

	public static void main(String[] args) {
		int i = 0;
		boolean flag = false;
		char myChar = 'A';
		String str1 = "1234";
		
		//
		Integer myInt2 = 100;
		Integer myInt1 = 100;
		
		
		Integer myInt = Integer.valueOf(str1);
		int result = myInt + 200;
		
		System.out.println(result);
		
		String str = "sourabh";
		
		//Handling with string's
		String str2 = "shruti";
		String str3 = "shruti";
		
		//Condition check in java two type
		//Type 1 - equality using "==" operator.
		if(str2 == str3){
			System.out.println("Inside Type 1 if condition");
		}
		
		//Type 2 - equality check "equals" method.
		if(str2.equals(str3)){
			System.out.println("Inside Type 2 if condition");
		}else if(str2 == str3){
			
		}else{
		
		
		}
		
		
		ClassA a = new ClassA();
		a = new ClassA();
		
		String myStr = "Hi, I am Sourabh.I am from Sangli.";
		myStr = myStr.concat("Good Bye!");
		//myStr = myStr.substring(myStr.indexOf("Sourabh"), myStr.indexOf("Sangli"));
		
		String[] myStrArr = myStr.split(" ");
		
		for(String item:myStrArr){
			System.out.println(item);
		}
		
		
		Character myChar1 = Character.valueOf(myChar);
		
		System.out.println("Completed");
	}

}

package com.example.string;

public class StringPoolExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Example creating first without new keyword.
		String str1 = "sourabh";
		String str2 = new String("sourabh");
		String str3 = "sourabh";
		String str4 = new String("sourabh");
		
		//Example creating first with new keyword.
		String strr1 = new String("shruti");
		String strr2 = "shruti";
		String strr3 = new String("shruti");
		String strr4 = "shruti";
		
		System.out.println("Completed");
		
		/*
		 * Result and Observations : 
		 * First example - 
		 * 		- str1 and str3 are pointing to same String pool object.
		 * 		- str2 and str4 are new objects created in memory.
		 * 		- Using new keyword even after having string present in string pool, will create new String.
		 * 
		 * Second example -
		 * 		- strr2 and strr4 are pointing to same String pool object.
		 * 		- strr1 and strr3 are new objects created in memory.
		 * 		- Even after you created first String object with new keyword, 
		 * 			Second created without using new keyword wont point to same one.
		 * 			For without using new keyword it will always create new one for first time.
		 * 
		 *  Final Conclusion - New keyword will always create new String object in memory, 
		 *  			while String created using double quotes will create new object for first time then point other references to same one.
		 *  			Even after one String object created already using new keyword.
		 *  
		 */
	}

}

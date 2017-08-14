package com.example.oops;

public class Employee {
	private int id;
	private String name;
	protected char gender;
	private int salary;
	
	
	public Employee(int id,String name, char gender, int salary) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.salary = salary;
	}
	
	Employee() {
		
	}

	public int getId() {
		return id;
	}

	/*public void setId(int id) {
		this.id = id;
	}*/

	public String getName() {
		return name;
	}

	/*public void setName(String name) {
		this.name = name;
	}
*/
	public char getGender() {
		return gender;
	}

	/*public void setGender(char gender) {
		this.gender = gender;
	}*/

	public int getSalary() {
		return salary;
	}

	/*public void setSalary(int salary) {
		this.salary = salary;
	}*/

	void methodA(){
		
	}
	
	void methodB(){
		
	}
}

package com.example.sample;

public class Student {
	int id;
	String name;
	String email;
	int mark;
	
	Student(){
	}
	
	public Student(String name, String email, int mark){
		this.name = name;
		this.email = email;
		this.mark = mark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}
}

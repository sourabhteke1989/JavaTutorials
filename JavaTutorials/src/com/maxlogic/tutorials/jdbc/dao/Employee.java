package com.maxlogic.tutorials.jdbc.dao;

public class Employee {
	public static final String EMP_ID = "EMP_ID";
	public static final String EMP_NAME = "EMP_NAME";
	public static final String EMP_SALARY = "EMP_SALARY";
	public static final String EMP_MANAGER_ID = "EMP_MANAGER_ID";
	public static final String EMP_GRADE = "EMP_GRADE";
	
	public enum EmpGrade{
		A("A"),
		B("B"),
		C("C"),
		D("D"),
		E("E");
		
		private String value;
		
		EmpGrade(String value){
			this.value = value;
		}
		
		public String getValue(){
			return value;
		}
	};
	
	private int empId;
	private String empName;
	private long empSalary;
	private int empMngrId;
	private EmpGrade empGrade;
	
	public Employee(){
	}
	
	public Employee(int empId, String empName, long empSalary, int mngrId, EmpGrade empGrade){
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.empMngrId = mngrId;
		this.empGrade = empGrade;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public long getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(long empSalary) {
		this.empSalary = empSalary;
	}
	public int getEmpMngrId() {
		return empMngrId;
	}
	public void setEmpMngrId(int empMngrId) {
		this.empMngrId = empMngrId;
	}
	public EmpGrade getEmpGrade() {
		return empGrade;
	}
	public void setEmpGrade(EmpGrade empGrade) {
		this.empGrade = empGrade;
	}
	
	
}

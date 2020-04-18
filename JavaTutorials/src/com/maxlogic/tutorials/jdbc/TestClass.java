package com.maxlogic.tutorials.jdbc;

import java.sql.SQLException;

import com.maxlogic.tutorials.jdbc.dao.Employee;
import com.maxlogic.tutorials.jdbc.dao.Employee.EmpGrade;
import com.maxlogic.tutorials.jdbc.dao.EmployeeDao;
import com.maxlogic.tutorials.jdbc.dao.EmployeeDaoImpl;

public class TestClass {

	public static void main(String[] args) {
		
		//Saving new employee to table
		/*Employee emp = new Employee("Shantanu", 10000, 2, EmpGrade.D);
		EmployeeDao empDao = new EmployeeDaoImpl();
		try {
			emp = empDao.saveEmployee(emp);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
		//Fetching employee by ID.
		/*EmployeeDao empDao = new EmployeeDaoImpl();
		try {
			Employee emp = empDao.getEmployeeById(3);
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		//Updating employee
		/*Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setEmpName("Sourabh");
		emp.setEmpSalary(80000);
		emp.setEmpGrade(EmpGrade.A);
		EmployeeDao empDao = new EmployeeDaoImpl();
		try {
			empDao.updateEmployee(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ 
		 
	}
}

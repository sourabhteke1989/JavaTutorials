package com.maxlogic.tutorials.jdbc.dao;

import java.sql.SQLException;

public interface EmployeeDao {
	public Employee getEmployeeById(int id) throws SQLException;
	public Employee saveEmployee(Employee emp) throws SQLException;
	public void updateEmployee(Employee emp) throws SQLException;
	public void deleteEmployee(int empId) throws SQLException;
}
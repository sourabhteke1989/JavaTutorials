package com.maxlogic.tutorials.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.maxlogic.tutorials.jdbc.ConnectionFactory;
import com.maxlogic.tutorials.jdbc.dao.Employee.EmpGrade;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee getEmployeeById(int id) throws SQLException {
		
		Employee emp = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs != null && rs.next()){
				emp = new Employee();
				emp.setEmpId(id);
				emp.setEmpName(rs.getString(Employee.EMP_NAME));
				emp.setEmpSalary(rs.getLong(Employee.EMP_SALARY));
				emp.setEmpMngrId(rs.getInt(Employee.EMP_MANAGER_ID));
				emp.setEmpGrade(EmpGrade.valueOf(rs.getString(Employee.EMP_GRADE)));
			}
			ConnectionFactory.close(con, stmt, rs);
		} catch (SQLException ex) {
			ConnectionFactory.close(con, stmt, rs);
			System.out.println("Error while fetching Employee from database");
			ex.printStackTrace();
			throw ex;
		}
		
		return emp;
	}

	@Override
	public Employee saveEmployee(Employee emp) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection con = ConnectionFactory.getConnection();
		int nextId = 0;
		
		String sequenceSQL = "SELECT EMP_SEQ.NEXTVAL FROM DUAL";
		String sql = "INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_SALARY, EMP_MANAGER_ID, EMP_GRADE)"
				+ "	VALUES (?, ?, ?, ?, ?)";
		try {
			stmt = con.prepareStatement(sequenceSQL);
			synchronized (stmt) {
				rs = stmt.executeQuery();
				if(rs != null && rs.next()){
					nextId = rs.getInt(1);
				}
			}
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, nextId);
			stmt.setString(2, emp.getEmpName());
			stmt.setLong(3, emp.getEmpSalary());
			stmt.setInt(4, emp.getEmpMngrId());
			stmt.setString(5, emp.getEmpGrade().getValue());
			
			int result = stmt.executeUpdate();
			
			if(result == 0){
				throw new SQLException("Employee record not inserted");
			}else{
				emp.setEmpId(nextId);
			}
			
			ConnectionFactory.close(con, stmt, rs);
		} catch (SQLException e) {
			ConnectionFactory.close(con, stmt, rs);
			System.out.println("Error while saving employee into database");
			e.printStackTrace();
			throw e;
		}
		return emp;
	}

	@Override
	public void updateEmployee(Employee emp) throws SQLException {
		
		PreparedStatement stmt = null;
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "UPDATE EMPLOYEE SET EMP_NAME=?, EMP_SALARY=?, EMP_MANAGER_ID=?, EMP_GRADE=? WHERE EMP_ID=?";
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, emp.getEmpName());
			stmt.setLong(2, emp.getEmpSalary());
			stmt.setInt(3, emp.getEmpMngrId());
			stmt.setString(4, emp.getEmpGrade().getValue());
			stmt.setInt(5, emp.getEmpId());
			
			int result = stmt.executeUpdate();
			if(result == 0){
				throw new SQLException("Error in updating employee record in DB");
			}
			
			ConnectionFactory.close(con, stmt, null);
		} catch (SQLException e) {
			ConnectionFactory.close(con, stmt, null);
			System.out.println("Employee.updateEmployee: Error while creating statement");
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void deleteEmployee(int empId) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
		
		try {
			stmt = con.prepareStatement(sql);
		
			stmt.setInt(0, empId);
			int result = stmt.executeUpdate();
			if(result == 0){
				throw new SQLException("Error while deleting employee record from DB");
			}
			
			ConnectionFactory.close(con, stmt, null);
		} catch (SQLException e) {
			ConnectionFactory.close(con, stmt, null);
			System.out.println("EmployeeDAOImpl.deleteEmployee: Error while creating statement");
			e.printStackTrace();
			throw e;
		}
	}

}

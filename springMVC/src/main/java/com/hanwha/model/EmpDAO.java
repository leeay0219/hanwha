package com.hanwha.model;

import java.util.*;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanwha.util.DBUtil_Oracle;
import java.sql.*;
import java.sql.Date;

@Repository
public class EmpDAO {
	@Autowired
	DataSource ds; 
	
	public List<Integer> selectAllManager() {
		List <Integer> mlist = new ArrayList<>(); 
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select distinct manager_id from employees";
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				mlist.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mlist; 
	}
	
	public List<String> selectAllJob() {
		List <String> joblist = new ArrayList<>(); 
		Connection conn = null; 
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from jobs";
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				joblist.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return joblist; 
	}
	
	public List <EmpVO> selectByJob(String job, int sal) {
		List <EmpVO> emplist = new ArrayList<>(); 
		Connection conn = null; 
		ResultSet rs = null;
		PreparedStatement st = null;
		String sql = "Select * from employees where job_id = ? and salary >= ?"; //  job_id = '\" + job + \"'\"==  �� �൵ �ȴ�
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, job);
			st.setInt(2, sal);
			rs = st.executeQuery();
			while (rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String phone_number = rs.getString("phone_number");
				Date hire_date = rs.getDate("hire_date");
				String job_id = rs.getString("job_id");
				int salary = rs.getInt("salary");
				double commission_pct = rs.getDouble("commission_pct");
				int manager_id = rs.getInt("manager_id");
				int department_id = rs.getInt("department_id");
				
				EmpVO emp = new EmpVO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emplist; 
	}
	
	public List <EmpVO> selectByDept(int deptid) {
		List <EmpVO> emplist = new ArrayList<>(); 
		Connection conn = null; 
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from employees where department_id = " + deptid;
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String phone_number = rs.getString("phone_number");
				Date hire_date = rs.getDate("hire_date");
				String job_id = rs.getString("job_id");
				int salary = rs.getInt("salary");
				double commission_pct = rs.getDouble("commission_pct");
				int manager_id = rs.getInt("manager_id");
				int department_id = rs.getInt("department_id");
				
				EmpVO emp = new EmpVO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emplist; 
	}
	public List <EmpVO> selectAll() {
		List <EmpVO> emplist = new ArrayList<>(); 
		Connection conn = null; 
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from employees";
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String phone_number = rs.getString("phone_number");
				Date hire_date = rs.getDate("hire_date");
				String job_id = rs.getString("job_id");
				int salary = rs.getInt("salary");
				double commission_pct = rs.getDouble("commission_pct");
				int manager_id = rs.getInt("manager_id");
				int department_id = rs.getInt("department_id");
				
				EmpVO emp = new EmpVO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emplist; 
	}

	public EmpVO selectById(int empid) {
		EmpVO emp = null; 
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "Select * from employees where employee_id = " + empid;
		
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int employee_id = rs.getInt("employee_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String email = rs.getString("email");
				String phone_number = rs.getString("phone_number");
				Date hire_date = rs.getDate("hire_date");
				String job_id = rs.getString("job_id");
				int salary = rs.getInt("salary");
				double commission_pct = rs.getDouble("commission_pct");
				int manager_id = rs.getInt("manager_id");
				int department_id = rs.getInt("department_id");
				
				emp = new EmpVO(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp; 
	}
	
	public int insertEmp(EmpVO emp) {
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)"; 
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateEmp(EmpVO emp) {
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		String sql = "update employees set first_name = ?, last_name = ?, "
				+ "email = ?, phone_number = ?, hire_date = ?, job_id = ?, "
				+ "salary = ?, commission_pct = ?, manager_id = ?, department_id = ? "
				+ "where employee_id = ?"; 
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setInt(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			st.setInt(9, emp.getManager_id());
			st.setInt(10, emp.getDepartment_id());
			st.setInt(11, emp.getEmployee_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteEmp(int empid) {
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		String sql = "delete from employees where employee_id = ?"; 
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}

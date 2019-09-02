package com.hanwha.model;
import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanwha.util.DBUtil_Oracle; 

// DAO (Data Access Object) : Business logic
// <bean id = "deptDAO" class = "com.hanwha.model.DeptDAO/>
@Repository 
public class DeptDAO {
	@Autowired
	DataSource ds;
	
	public List <DeptDTO> selectAll() {
		List <DeptDTO> deptList = new ArrayList<>();
		//default: autocommit
		//conn.setAutoCommit(false); 로 autocommit 설정 막기 가능
		Statement st = null;
		ResultSet rs = null; 
		Connection conn = null;
		String sql = "select * from departments"; 
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int deptid = rs.getInt("department_id");
				String dname = rs.getString("department_name");
				DeptDTO dept = new DeptDTO(deptid, dname);
				deptList.add(dept);
			}
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil_Oracle.dbClose(rs, st, conn);
		} return deptList; 
	}

	public DeptDTO selectByID(int deptid) {
		DeptDTO dept = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null; 
		String sql = "select * from departments where department_id = " + deptid; 
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				String dname = rs.getString("department_name");
				dept = new DeptDTO(deptid, dname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil_Oracle.dbClose(rs, st, conn);
		} return dept; 
	}

	public int insertDept(DeptDTO dept) {
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		String sql = "insert into departments(department_id, department_name) values(?,?)"; 
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2, dept.getDepartment_name());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateDept(DeptDTO dept) {
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		
		String sql = "update departments set department_name = ? where department_id = ?"; 
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, dept.getDepartment_name());
			st.setInt(2, dept.getDepartment_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteDept(int deptid) {
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		String sql = "delete from departments where department_id = ?"; 
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

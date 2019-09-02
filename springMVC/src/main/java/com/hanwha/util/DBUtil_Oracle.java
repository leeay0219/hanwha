package com.hanwha.util;
import java.sql.*; 

public class DBUtil_Oracle {

	public static void dbClose(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection getConnection() {
		Connection con = null;
		// ���ݿ�����
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		// ���ӵ����ݿ�
		String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=PersonalScheduel";
		String userName = "sa";
		String userPwd = "sa";
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(dbURL, userName, userPwd);
		} catch (Exception e) {
			System.out.println("��ȡ����ʧ��." + e.getMessage());
		}
		return con;
	}

	
	public static void closeAll(ResultSet rs, PreparedStatement pst, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

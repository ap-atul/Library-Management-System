package db;

import java.sql.*;

public class InitDb {

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library?useSSL=false", "root", "root");

		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}

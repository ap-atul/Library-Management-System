package classes;

import java.sql.*;

import db.InitDb;

public class BookDB {
	public static int save(String a, String b, String c, int d) {
		int status = 0;
		try {
			Connection con = InitDb.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into book values(?,?,?,?)");
			ps.setString(1, a);
			ps.setString(2, b);
			ps.setString(3, c);
			ps.setInt(4, d);
			if (ps.execute())
				status = 1;
			System.out.println(status);
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static boolean search(String k) {
		boolean status = true;
		try {
			Connection con = InitDb.getConnection();
			con.createStatement();
			PreparedStatement ps = con.prepareStatement("select * from book where name = ?");
			ps.setString(1, k);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int del(String id) {
		int status = 0;
		try {
			Connection con = InitDb.getConnection();
			con.createStatement();
			PreparedStatement ps = con.prepareStatement("delete from book where name = ?");
			ps.setString(1, id);
			ps.execute();
			int a = ps.getUpdateCount();
			if (a > 0)
				status = 1;
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int disp() {
		int status = 0;
		String n, a, p;
		String q;
		try {
			Connection con = InitDb.getConnection();
			con.createStatement();
			PreparedStatement ps = con.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();

			if (ps.isPoolable()) {
				while (rs.next()) {
					n = rs.getString("name");
					a = rs.getString("author");
					p = rs.getString("publication");
					q = rs.getString("quantity");
					System.out.println("\n1. Name :" + n + "\n2. Author :" + a + "\n3. Publication :" + p
							+ "\n4. Quanity :" + q + "");
				}
				status = 1;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static ResultSet search_book(String k) {
		ResultSet rs = null;
		try {
			Connection con = InitDb.getConnection();
			con.createStatement();
			PreparedStatement ps = con.prepareStatement("select * from book where name = ?");
			ps.setString(1, k);
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;
	}

	// @SuppressWarnings("null")
	public static String dis() {
		String arr = null;
		String n;
		try {
			Connection con = InitDb.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from book");
			ResultSet rs = ps.executeQuery();

			if (ps.isPoolable()) {
				while (rs.next()) {
					n = rs.getString("name");
					rs.getString("author");
					rs.getString("publication");
					rs.getString("quantity");
					arr = n;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return arr;
	}
}

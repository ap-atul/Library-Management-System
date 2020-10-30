package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.InitDb;

public class IssueDB {
	public static int delete(String bname, int sid) {
		int status = 0;
		try {
			Connection con = InitDb.getConnection();

			status = update(bname);// updating quantity and issue

			if (status > 0) {
				PreparedStatement ps = con.prepareStatement("delete from issue where b_name = ? and s_id = ?");
				ps.setString(1, bname);
				ps.setInt(2, sid);
				status = ps.executeUpdate();
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int update(String bname) {
		int status = 0;
		int quantity = 0;
		try {
			Connection con = InitDb.getConnection();

			PreparedStatement ps = con.prepareStatement("select quantity from book where name = ?");
			ps.setString(1, bname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
			}

			if (quantity >= 0) {
				PreparedStatement ps2 = con.prepareStatement("update book set quantity = ? where name = ?");
				ps2.setInt(1, quantity + 1);
				ps2.setString(2, bname);

				status = ps2.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int save(String studentname, int roll, String bname, int sid) {
		int status = 0;
		try {
			Connection con = InitDb.getConnection();

			status = updatebook(bname);// updating quantity and issue

			if (status > 0) {
				PreparedStatement ps = con.prepareStatement(
						"insert into issue(s_name,s_roll,b_name,date_of_issue,s_id) values(?,?,?,CURDATE(),?)");
				ps.setString(1, studentname);
				ps.setInt(2, roll);
				ps.setString(3, bname);
				ps.setInt(4, sid);
				status = ps.executeUpdate();
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int updatebook(String bname) {
		int status = 0;
		int quantity = 0;
		try {
			Connection con = InitDb.getConnection();

			PreparedStatement ps = con.prepareStatement("select quantity from book where name = ?");
			ps.setString(1, bname);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				quantity = rs.getInt("quantity");
			}

			if (quantity > 0) {
				PreparedStatement ps2 = con.prepareStatement("update book set quantity = quantity - 1 where name = ?");
				System.out.println("Book updated");
				ps2.setString(1, bname);

				status = ps2.executeUpdate();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}

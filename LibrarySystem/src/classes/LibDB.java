package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LibDB {
	public static int save(String a,int b,String c,long d) {
		int status =0;
		try {
		Connection con=initDB.getConnection();
		PreparedStatement ps=con.prepareStatement("insert into librarian values(?,?,?,?)");
		ps.setString(1,a);
		ps.setInt(2,b);
		ps.setString(3,c);
		ps.setLong(4,d);
		status =  ps.executeUpdate();
		}
		catch(Exception e) {System.out.println(e);}
		return status;
	}
	public static int del(int id) {
		int status= 0;
		try {
			Connection con=initDB.getConnection();
			con.createStatement();  
			PreparedStatement ps=con.prepareStatement("delete from librarian where id = ?");
			ps.setInt(1,id);
			ps.execute();
			int a = ps.getUpdateCount();
			System.out.println(a);
			if(a > 0)
				status=1;				
		}
			catch(Exception e) {System.out.println(e);}
		return status;
	}
	public static int disp() {
		int status= 0;
		String n,a;
		int id,contact;
		try {
			Connection con=initDB.getConnection();
			con.createStatement();  
			PreparedStatement ps=con.prepareStatement("select * from librarian");
			ResultSet rs=ps.executeQuery();
			
			if(ps.isPoolable())
			{
				while(rs.next()) {
					n=rs.getString("name");
					a=rs.getString("address");
					id=rs.getInt("id");
					contact=rs.getInt("contact");
					System.out.println("1. Name :"+n+"\n2. Address :"+a+"\n3. ID :"+id+"\n4. Contact :"+contact+"");
				}
				status = 1;
			}
		}
			catch(Exception e) {System.out.println(e);}
		return status;
	}
	public static ResultSet disp_all() {
		ResultSet rs = null;
		try {
			Connection con=initDB.getConnection();
			con.createStatement();  
			PreparedStatement ps=con.prepareStatement("select * from librarian");
			rs=ps.executeQuery();
		}
		catch(Exception e) {}
		return rs;
	}
}

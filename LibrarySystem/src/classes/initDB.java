package classes;

import java.sql.*;

public class initDB {

	public static Connection getConnection(){
		Connection con=null;
		try{ 
		    con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library?useSSL=false","root","root");
		    
	    }catch(Exception e){ System.out.println(e);}
		return con;
	}
}

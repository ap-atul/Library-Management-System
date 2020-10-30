package frontPage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;

import com.sun.rowset.CachedRowSetImpl;

class Student{
	@SuppressWarnings("unused")
	private static String ip = "127.0.0.1";
	private static Socket socket = null;
	
	public static ResultSet call(String query, String ip) {
		ResultSet rs = null;
		CachedRowSetImpl crs = null;
		
			try{
				System.out.println(ip);
				socket = new Socket(ip, 4045);
				System.out.println("Connected");
				ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.flush();
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			
				while(socket != null){
					outputStream.writeObject(query);
					System.out.println("Data written");
					
					crs = (CachedRowSetImpl) inputStream.readObject();
					rs = crs.getOriginal();
					
				    if(rs != null)
				    	break;
				}

				socket.close();
				
			}catch(Exception e){System.out.println(e);}
			
		return rs;
	}
	
	public static String getIp() {
		return ip;
	}
}

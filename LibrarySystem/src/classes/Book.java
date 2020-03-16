package classes;

import java.util.*;
import java.net.*;
import java.io.*;


public class Book implements Serializable{
	String bname,author,publ;
	int quantity;
	
	private Socket socket = null;
	private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;
	private static final long serialVersionUID = 1L;
	
	Scanner sc=new Scanner(System.in);
	final HashSet<Book> hs=new HashSet<Book>();
	Iterator<Book> it;
	
	Book b;
	
	public void add_books(){	
		System.out.println("Enter the book name ::");
		bname=sc.next();
		System.out.println("Enter the author name ::");
		author=sc.next();
		System.out.println("Enter the publication name ::");
		publ=sc.next();
		System.out.println("Enter number of copies ::");
		quantity=sc.nextInt();
		
		if(BookDB.save(bname, author, publ, quantity) == 0)
			System.out.println("Book Added Successfully");
		else
			System.out.println("Book Not Added Successfully");
	}
	
	public void display_books(){
		if(BookDB.disp() == 0)
			System.out.println("No data found");
	}
	
	public boolean search_book(){
		String key;
		it=hs.iterator();
		
		System.out.println("Enter the name of the book to search ::");
		key=sc.next();
		
		if(BookDB.search(key)) {
			System.out.println("Book Found");
			return true;
		}
		else {
			System.out.println("Book Not Found");
			return false;
		}
	}
	
	public void update_book(){
		String key;
		it=hs.iterator();
		
		System.out.println("Enter the name of the book to search ::");
		key=sc.next();
		
		if(BookDB.del(key) == 0)
		{
			System.out.println("Enter the author name ::");
			author=sc.next();
			System.out.println("Enter the publication name ::");
			publ=sc.next();
			System.out.println("Enter number of copies ::");
			quantity=sc.nextInt();
			
			if(BookDB.save(bname, author, publ, quantity) == 0)
				System.out.println("Book Added Successfully");
		}
		else
			System.out.println("Book does not exists");	
	}
	
	public void del_book(){
		String key;
		
		System.out.println("Enter the name of the book to search ::");
		key=sc.next();
		
		if(BookDB.del(key) == 0)
			System.out.println("Book deleted successfully");
		else
			System.out.println("Book does not exist");
	}
	
	public void communicate(Book b){
		try {
			socket = new Socket("localHost", 4045);
			System.out.println("Connected");
			inputStream = new ObjectInputStream(socket.getInputStream());
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			
			while(!socket.isClosed()) {
			String option=(String) inputStream.readObject();
			
			
			
			//Checking the request made
			//Sending all Books
			if(option.toUpperCase().equals("ALL")) {
				System.out.println("Option recieved for all books");
				
				if(hs.size() == 0)
					outputStream.writeObject(null);
				else
					outputStream.writeObject("all ok");
				
				it=hs.iterator();
				while(it.hasNext())
				{
					b=new Book();
					b=it.next();
					outputStream.writeObject(b.bname);
					outputStream.writeObject(b.author);
					outputStream.writeObject(b.publ);
					outputStream.writeObject(b.quantity);
				}
			}
			
			//Sending particular book
			else if(option.toUpperCase().equals("SEARCH")) {
				
				String key=(String) inputStream.readObject();
				System.out.println("Option recieved for search");
				it=hs.iterator();
				
				if(hs.size() == 0)
					outputStream.writeObject(null);
				else
					outputStream.writeObject("all ok");
				
				int f=0;//no book found
				while(it.hasNext())
				{
					b=new Book();
					b=it.next();
					if(b.bname.equals(key)) {
						outputStream.writeObject(b.bname);
						outputStream.writeObject(b.author);
						outputStream.writeObject(b.publ);
						outputStream.writeObject(b.quantity);
						f=1;
						}
				}
				if (f == 0)
				{
					System.out.println("Book not found");
				}
				
			}
			socket.close();
			
			}//while end
		}
		catch(Exception e) {
			System.out.println("Data cannot be sent");
		}	
	}
}

package classes;

import java.util.*;

public class Librarian {

	String sname;
	int sroll, sid;

	Scanner sc = new Scanner(System.in);
	Vector<Librarian> v = new Vector<Librarian>();

	Librarian lb;
	Book b;
	Iterator<Librarian> it;

	public void issue() {
		String key;
		System.out.println("Enter the name of the book ::");
		key = sc.next();
		if (BookDB.search(key)) {
			System.out.println("Enter the student id ::");
			sid = sc.nextInt();
			System.out.println("Enter the student roll ::");
			sroll = sc.nextInt();
			System.out.println("Enter the student name ::");
			sname = sc.next();

			if (IssueDB.save(sname, sroll, key, sid) == 0)
				System.out.println("Book Issued");
		} else
			System.out.println("Book does not exists");
	}

	public void return_book() {
		int key;
		System.out.println("Enter the student id ::");
		key = sc.nextInt();
		String book;
		System.out.println("Enter the name of the book ::");
		book = sc.next();

		if (IssueDB.delete(book, key) == 1)
			System.out.println("Book returned");
		else
			System.out.println("No Book issued");
		/*
		 * it=v.iterator(); while(it.hasNext()) { lb=new Librarian(); lb=it.next();
		 * if(lb.sid == key) { v.remove(lb); System.out.println("Book returned");
		 * return; } } System.out.println("No Book issued");
		 */
	}

	public void display_All() {
		it = v.iterator();
		while (it.hasNext()) {
			lb = new Librarian();
			lb = it.next();
			System.out.println("Student id ::" + lb.sid);
			System.out.println("Student name ::" + lb.sname);
			System.out.println("Student roll ::" + lb.sroll);
		}
	}
}

package classes;

import java.util.*;

public class Admin {

	String user, pass;
	String lnm, laddr;
	int id, mob;

	final LinkedList<Admin> l = new LinkedList<Admin>();
	Iterator<Admin> it;
	Scanner sc = new Scanner(System.in);
	Admin admin;

	public Admin() {
	}

	public void addLibrarian() {
		System.out.println("Enter librarian id ::");
		id = sc.nextInt();
		System.out.println("Enter librarian name ::");
		lnm = sc.next();
		System.out.println("Enter librarian contact ::");
		mob = sc.nextInt();
		System.out.println("Enter librarian address ::");
		laddr = sc.next();
		System.out.println("Enter librarian username ::");
		user = sc.next();
		System.out.println("Enter librarian password ::");
		pass = sc.next();

		if (LibDB.save(lnm, id, laddr, mob) == 0 && AdminDB.save(user, pass) == 0)
			System.out.println("Librarian can now login");
		else
			System.out.println("Data adding failed");
	}

	public void deleteLibrarian() {
		int key;

		System.out.println("Enter the id of the librarian to search :");
		key = sc.nextInt();

		if (LibDB.del(key) == 1)
			System.out.println("Librarian deleted successfully");
		else
			System.out.println("Librarian does not exist");
	}

	public void display_all() {
		if (LibDB.disp() == 1)
			System.out.println("No data found");
	}

	public void update_Lib() {
		int key;
		it = l.iterator();

		System.out.println("Enter the id of the librarian to search :");
		key = sc.nextInt();

		if (LibDB.del(key) == 1) {
			System.out.println("Enter librarian name ::");
			lnm = sc.next();
			System.out.println("Enter librarian contact ::");
			mob = sc.nextInt();
			System.out.println("Enter librarian address ::");
			laddr = sc.next();
			System.out.println("Enter librarian username ::");
			user = sc.next();
			System.out.println("Enter librarian password ::");
			pass = sc.next();

			if (LibDB.save(lnm, id, laddr, mob) == 0)
				System.out.println("Librarian updated successfully");
		} else
			System.out.println("Librarian does not exist");
	}

	public boolean Login() {
		System.out.println("Enter username ::");
		String u = sc.next();
		System.out.println("Enter password ::");
		String p = sc.next();

		if (AdminDB.login(u, p) == 1)
			return true;
		return false;
	}
}

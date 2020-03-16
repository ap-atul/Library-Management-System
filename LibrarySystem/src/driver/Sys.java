package driver;
import classes.*;
import java.util.*;

public class Sys {
	public static void main(String args[])
	{
		Book b;
		Admin a=new Admin();
		Librarian l;
		
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		
		int op,op1,op2;
		
		do {
			System.out.println("\n1.Admin\n2.Librarian\n3.Exit");
			op=sc.nextInt();
			
			switch(op)
			{
			case 1:
				if(a.Login())
				{
					do {
						System.out.println("\n1.Add Librarian\n2.Delete Librarian\n3.Update Librarian\n4.Display all Librarians\n5.Exit");
						op1=sc.nextInt();
						switch(op1)
						{
						case 1:a.addLibrarian();break;
						case 2:a.deleteLibrarian();break;
						case 3:a.update_Lib();break;
						case 4:a.display_all();break;
						}	
					}while(op1 != 5);
				}
				else
					System.out.println("Try again!");
				break;
			case 2:
				b=new Book();
				l=new Librarian();
				if(a.Login()) {
					do {
						System.out.println("\n1.Issue Book\n2.Return Book\n3.Display all issued books\n4.Send data through Socket\n5.Add Books\n6.Delete Book\n7.Update Book\n8.Display all Books\n9.Search Book\n10.Exit");
						op2=sc.nextInt();
						
						switch(op2)
						{
						case 1:l.issue();break;
						case 2:l.return_book();break;
						case 3:l.display_All();break;
						case 4:b.communicate(b);break;
						case 5:b.add_books();break;
						case 6:b.del_book();break;
						case 7:b.update_book();break;
						case 8:b.display_books();break;
						case 9:b.search_book();break;
						}
					}while(op2 != 10);
				}
				else
					System.out.println("Try again!");
				break;
			}
		}while(op != 3);
	}
}

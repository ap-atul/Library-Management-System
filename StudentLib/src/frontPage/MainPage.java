package frontPage;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JTextField;
import frontPage.Student;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;

public class MainPage {

	JFrame frame;
	
	private JTable table;
	private JTextField search;
	private JTextField input_ip;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage window = new MainPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Library Enquiry System");
		frame.setBounds(100, 100, 721, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 705, 445);
		frame.getContentPane().add(tabbedPane);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Search Books", null, desktopPane_1, null);
		
		JLabel label_1 = new JLabel("Enter Book name");
		label_1.setBounds(64, 25, 135, 35);
		desktopPane_1.add(label_1);
		
		search = new JTextField();
		search.setColumns(10);
		search.setBounds(255, 22, 191, 40);
		desktopPane_1.add(search);
		
		
		JLabel addr = new JLabel("Server Address");
		addr.setBounds(580, 33, 120, 30);
		desktopPane_1.add(addr);
		input_ip = new JTextField();
		input_ip.setColumns(10);
		input_ip.setBounds(580, 10, 120, 30);
		desktopPane_1.add(input_ip);

		
		final JLabel l_name = new JLabel("");
		l_name.setBounds(294, 162, 152, 35);
		desktopPane_1.add(l_name);
		
		final JLabel l_author = new JLabel("");
		l_author.setBounds(294, 221, 152, 35);
		desktopPane_1.add(l_author);
		
		final JLabel l_pub = new JLabel("");
		l_pub.setBounds(294, 283, 152, 35);
		desktopPane_1.add(l_pub);
		
		final JLabel l_quan = new JLabel("");
		l_quan.setBounds(294, 338, 152, 35);
		desktopPane_1.add(l_quan);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
				String key = search.getText();
				String s = input_ip.getText();
				try {
					String query = "select * from book where name = '" + key + "'";
					ResultSet rs = Student.call(query, s);
					rs.next();
					if(rs != null) {
							l_name.setText(rs.getString("name"));
							l_author.setText(rs.getString("author"));
							l_pub.setText(rs.getString("publication"));
							l_quan.setText(rs.getString("quantity"));
					}
				}catch(Exception e) {}
			}
			public void clear() {
				l_name.setText(null);
				l_author.setText(null);
				l_pub.setText(null);
				l_quan.setText(null);
			}
		});
		btnNewButton.setBounds(190, 93, 97, 35);
		desktopPane_1.add(btnNewButton);
		
		JLabel lblNameOfBook = new JLabel("Name of Book");
		lblNameOfBook.setBounds(78, 162, 162, 35);
		desktopPane_1.add(lblNameOfBook);
		
		JLabel lblAuthorOfBook = new JLabel("Author of Book");
		lblAuthorOfBook.setBounds(78, 221, 152, 35);
		desktopPane_1.add(lblAuthorOfBook);
		
		JLabel lblPublicationOfBook = new JLabel("Publication of Book");
		lblPublicationOfBook.setBounds(78, 283, 152, 35);
		desktopPane_1.add(lblPublicationOfBook);
		
		JLabel lblNumberOfBooks = new JLabel("Number of Book(s)");
		lblNumberOfBooks.setBounds(78, 338, 152, 35);
		desktopPane_1.add(lblNumberOfBooks);
				
		final JDesktopPane desktopPane_4 = new JDesktopPane();
		desktopPane_4.setBackground(Color.WHITE);
		tabbedPane.addTab("All Books", null, desktopPane_4, null);
		desktopPane_4.setLayout(null);
		
		final JLabel label_8 = new JLabel("");
		label_8.setBounds(20, 45, 645, 361);
		desktopPane_4.add(label_8);
		
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String data[][]=null;
				String column[]=null;
				String s = input_ip.getText();
				try{
					String query = "select * from book";
					ResultSet rs= Student.call(query, s);
					
					java.sql.ResultSetMetaData rsmd=rs.getMetaData();
					int cols=rsmd.getColumnCount();
					column=new String[cols];
					for(int i=1;i<=cols;i++){
						column[i-1]=rsmd.getColumnName(i);
					}
					
					rs.last();
					int rows=rs.getRow();
					rs.beforeFirst();

					data=new String[rows][cols];
					int count=0;
					while(rs.next()){
						for(int i=1;i<=cols;i++){
							data[count][i-1]=rs.getString(i);
						}
						count++;
					}
				}catch(Exception e){System.out.println(e);}
				
				table = new JTable(data,column);
				//table.setBounds(10, 50, 400, 400);
				//desktopPane_4.add(table);
				JScrollPane sp=new JScrollPane(table);
				table.setEnabled(false);
				sp.setBounds(10, 60, 721, 475);
				desktopPane_4.add(sp);
			}
		});
		btnDisplay.setBounds(10, 11, 89, 23);
		desktopPane_4.add(btnDisplay);
				
	}
}


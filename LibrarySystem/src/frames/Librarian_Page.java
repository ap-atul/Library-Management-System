package frames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JTextField;

import classes.*;
import db.InitDb;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Librarian_Page {

	JFrame frame;
	private JTable table;
	private JTextField bname;
	private JTextField bauthor;
	private JTextField bpubl;
	private JTextField bquan;
	private JTextField search;
	private JTextField dname;
	private JTextField u_name;
	private JTextField u_author;
	private JTextField u_publ;
	private JTextField u_no;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Librarian_Page window = new Librarian_Page();
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
	public Librarian_Page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Library Management System");
		frame.setBounds(100, 100, 721, 484);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 705, 445);
		frame.getContentPane().add(tabbedPane);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Add Books", null, desktopPane, null);

		bname = new JTextField();
		bname.setBounds(292, 25, 191, 40);
		desktopPane.add(bname);
		bname.setColumns(10);

		bauthor = new JTextField();
		bauthor.setColumns(10);
		bauthor.setBounds(292, 89, 191, 40);
		desktopPane.add(bauthor);

		bpubl = new JTextField();
		bpubl.setColumns(10);
		bpubl.setBounds(292, 149, 191, 40);
		desktopPane.add(bpubl);

		bquan = new JTextField();
		bquan.setColumns(10);
		bquan.setBounds(292, 220, 191, 40);
		desktopPane.add(bquan);

		JLabel lblEnterBookName = new JLabel("Enter Book name");
		lblEnterBookName.setBounds(101, 28, 135, 35);
		desktopPane.add(lblEnterBookName);

		JLabel lblEnterAuthorName = new JLabel("Enter Author name");
		lblEnterAuthorName.setBounds(101, 92, 135, 35);
		desktopPane.add(lblEnterAuthorName);

		JLabel lblEnterPublicationName = new JLabel("Enter Publication name");
		lblEnterPublicationName.setBounds(101, 152, 167, 35);
		desktopPane.add(lblEnterPublicationName);

		JLabel lblEnterNumberOf = new JLabel("Enter number of books");
		lblEnterNumberOf.setBounds(101, 223, 167, 35);
		desktopPane.add(lblEnterNumberOf);

		final JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(101, 352, 382, 14);
		desktopPane.add(label);

		JButton addb = new JButton("Add Book");
		addb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bn = bname.getText();
				String au = bauthor.getText();
				String pub = bpubl.getText();
				int q = Integer.parseInt(bquan.getText());

				if (BookDB.save(bn, au, pub, q) == 0)
					label.setText("Book added successfully!");
				else
					label.setText("Book adding failed!");

				clear();
			}

			public void clear() {
				bname.setText(null);
				bauthor.setText(null);
				bpubl.setText(null);
				bquan.setText(null);
			}
		});
		addb.setBounds(230, 304, 105, 37);
		desktopPane.add(addb);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LibChoice l = new LibChoice();
				l.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(0, 394, 70, 23);
		desktopPane.add(btnBack);

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
				try {
					ResultSet rs = BookDB.search_book(key);
					rs.next();
					if (rs != null) {
						l_name.setText(rs.getString("name"));
						l_author.setText(rs.getString("author"));
						l_pub.setText(rs.getString("publication"));
						l_quan.setText(rs.getString("quantity"));
					}
				} catch (Exception e) {
				}
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

		JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Delete Books", null, desktopPane_2, null);

		JLabel label_2 = new JLabel("Enter Book name");
		label_2.setBounds(118, 14, 135, 35);
		desktopPane_2.add(label_2);

		dname = new JTextField();
		dname.setColumns(10);
		dname.setBounds(309, 11, 191, 40);
		desktopPane_2.add(dname);

		final JLabel label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(154, 149, 328, 28);
		desktopPane_2.add(label_3);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dn = dname.getText();

				if (BookDB.del(dn) == 1)
					label_3.setText("Book deleted successfully!");
				else
					label_3.setText("Book does not exist!");
				dname.setText(null);
			}
		});
		btnDelete.setBounds(244, 82, 97, 35);
		desktopPane_2.add(btnDelete);

		JDesktopPane desktopPane_3 = new JDesktopPane();
		desktopPane_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Update Books", null, desktopPane_3, null);

		JLabel label_4 = new JLabel("Enter Book name");
		label_4.setBounds(149, 11, 135, 35);
		desktopPane_3.add(label_4);

		u_name = new JTextField();
		u_name.setColumns(10);
		u_name.setBounds(340, 11, 191, 40);
		desktopPane_3.add(u_name);

		final JLabel dl = new JLabel("");
		dl.setHorizontalAlignment(SwingConstants.CENTER);
		dl.setBounds(165, 372, 341, 20);
		desktopPane_3.add(dl);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nm = u_name.getText();

				if (BookDB.search(nm)) {
					setDis(true);
					String au = u_author.getText();
					String pub = u_publ.getText();
					int q = Integer.parseInt(u_no.getText());

					BookDB.del(nm);
					if (BookDB.save(nm, au, pub, q) == 0)
						dl.setText("Book updated successfully!");

					clear();

				} else
					dl.setText("Bookd does not exists");
			}

			public void setDis(boolean b) {
				u_name.setEnabled(b);
				u_author.setEnabled(b);
				u_publ.setEnabled(b);
				u_no.setEnabled(b);
			}

			public void clear() {
				u_name.setText(null);
				u_author.setText(null);
				u_publ.setText(null);
				u_no.setText(null);
			}
		});
		btnUpdate.setBounds(272, 326, 97, 35);
		desktopPane_3.add(btnUpdate);

		JLabel label_5 = new JLabel("Enter Author name");
		label_5.setBounds(149, 85, 135, 35);
		desktopPane_3.add(label_5);

		u_author = new JTextField();
		u_author.setEnabled(false);
		u_author.setColumns(10);
		u_author.setBounds(340, 85, 191, 40);
		desktopPane_3.add(u_author);

		u_publ = new JTextField();
		u_publ.setEnabled(false);
		u_publ.setColumns(10);
		u_publ.setBounds(340, 145, 191, 40);
		desktopPane_3.add(u_publ);

		JLabel label_6 = new JLabel("Enter Publication name");
		label_6.setBounds(149, 148, 174, 35);
		desktopPane_3.add(label_6);

		JLabel label_7 = new JLabel("Enter number of books");
		label_7.setBounds(149, 219, 174, 35);
		desktopPane_3.add(label_7);

		u_no = new JTextField();
		u_no.setEnabled(false);
		u_no.setColumns(10);
		u_no.setBounds(340, 216, 191, 40);
		desktopPane_3.add(u_no);

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
				String data[][] = null;
				String column[] = null;
				try {
					java.sql.Connection con = InitDb.getConnection();
					java.sql.PreparedStatement ps = con.prepareStatement("select * from book",
							ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					ResultSet rs = ps.executeQuery();

					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					int cols = rsmd.getColumnCount();
					column = new String[cols];
					for (int i = 1; i <= cols; i++) {
						column[i - 1] = rsmd.getColumnName(i);
					}

					rs.last();
					int rows = rs.getRow();
					rs.beforeFirst();

					data = new String[rows][cols];
					int count = 0;
					while (rs.next()) {
						for (int i = 1; i <= cols; i++) {
							data[count][i - 1] = rs.getString(i);
						}
						count++;
					}
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}

				table = new JTable(data, column);
				// table.setBounds(10, 50, 400, 400);
				// desktopPane_4.add(table);
				JScrollPane sp = new JScrollPane(table);
				table.setEnabled(false);
				sp.setBounds(10, 60, 721, 475);
				desktopPane_4.add(sp);
			}
		});
		btnDisplay.setBounds(10, 11, 89, 23);
		desktopPane_4.add(btnDisplay);

	}
}

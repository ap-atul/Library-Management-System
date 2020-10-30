package frames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import classes.AdminDB;
import classes.LibDB;
import db.InitDb;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.Toolkit;

public class Admin_Page {

	JFrame frame;
	static Admin_Page admin_page;
	private JTable table;
	private JTextField lname;
	private JTextField lid;
	private JTextField ladd;
	private JTextField lcon;
	private JTextField user;
	private JTextField pass;
	private JTextField did;
	private JTextField u_id;
	private JTextField u_name;
	private JTextField u_add;
	private JTextField u_con;
	private JTextField u_u;
	private JTextField u_p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_page = new Admin_Page();
					admin_page.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin_Page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(46, 139, 87));
		frame.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		frame.setForeground(new Color(218, 112, 214));
		
		frame.setTitle("Library Management System");
		frame.setBounds(100, 100, 718, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 6, 702, 464);
		frame.getContentPane().add(tabbedPane);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		tabbedPane.addTab("Add Libraran", null, desktopPane, null);
		tabbedPane.setBackgroundAt(0, Color.WHITE);

		lname = new JTextField();
		lname.setBackground(Color.WHITE);
		lname.setBounds(303, 21, 178, 35);
		desktopPane.add(lname);
		lname.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(141, 19, 118, 35);
		desktopPane.add(lblName);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(141, 65, 118, 35);
		desktopPane.add(lblId);

		lid = new JTextField();
		lid.setColumns(10);
		lid.setBackground(Color.WHITE);
		lid.setBounds(303, 67, 178, 35);
		desktopPane.add(lid);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(141, 111, 118, 35);
		desktopPane.add(lblAddress);

		ladd = new JTextField();
		ladd.setColumns(10);
		ladd.setBackground(Color.WHITE);
		ladd.setBounds(303, 113, 178, 35);
		desktopPane.add(ladd);

		JLabel lblContact = new JLabel("Contact");
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContact.setBounds(141, 157, 118, 35);
		desktopPane.add(lblContact);

		lcon = new JTextField();
		lcon.setColumns(10);
		lcon.setBackground(Color.WHITE);
		lcon.setBounds(303, 159, 178, 35);
		desktopPane.add(lcon);

		final JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(141, 391, 340, 23);
		desktopPane.add(label);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(141, 203, 118, 35);
		desktopPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(141, 249, 118, 35);
		desktopPane.add(lblPassword);

		user = new JTextField();
		user.setColumns(10);
		user.setBackground(Color.WHITE);
		user.setBounds(303, 205, 178, 35);
		desktopPane.add(user);

		pass = new JTextField();
		pass.setColumns(10);
		pass.setBackground(Color.WHITE);
		pass.setBounds(303, 251, 178, 35);
		desktopPane.add(pass);

		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Delete Librarian", null, desktopPane_1, null);

		did = new JTextField();
		did.setBounds(232, 37, 177, 42);
		desktopPane_1.add(did);
		did.setColumns(10);

		JLabel lblNewLabel = new JLabel("Enter id to delete");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(68, 44, 154, 28);
		desktopPane_1.add(lblNewLabel);

		JButton del = new JButton("Delete");

		final JLabel label_1 = new JLabel("");
		label_1.setBounds(68, 168, 372, 48);
		desktopPane_1.add(label_1);

		JButton addLib = new JButton("Submit");

		addLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nm = lname.getText().trim();
				int id = Integer.parseInt(lid.getText());
				String addr = ladd.getText().trim();
				long cont = Long.parseLong(lcon.getText());
				String username = user.getText();
				String password = pass.getText();

				if (LibDB.save(nm, id, addr, cont) == 1 && AdminDB.save(username, password) == 1) {
					label.setText("Librarian Added successfully!");
					clear();
				} else {
					label.setText("Librarian Adding Failed!");
					clear();
				}
			}

			public void clear() {
				lname.setText(null);
				ladd.setText(null);
				lid.setText(null);
				lcon.setText(null);
				user.setText(null);
				pass.setText(null);
			}
		});
		addLib.setBounds(234, 333, 130, 47);
		desktopPane.add(addLib);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogIn a = new LogIn();
				a.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(0, 413, 89, 23);
		desktopPane.add(btnBack);

		del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(did.getText());

				if (LibDB.del(id) == 1) {
					label_1.setText("Librarian deleted successfully!");
				} else
					label_1.setText("Librarian does not exists!");
				clear();
			}

			public void clear() {
				did.setText(null);
			}
		});
		del.setBounds(174, 109, 100, 36);
		desktopPane_1.add(del);

		JDesktopPane desktopPane_3 = new JDesktopPane();
		desktopPane_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Update Librarian", null, desktopPane_3, null);

		JLabel label_2 = new JLabel("Name");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(153, 72, 118, 35);
		desktopPane_3.add(label_2);

		u_id = new JTextField();
		u_id.setColumns(10);
		u_id.setBackground(Color.WHITE);
		u_id.setBounds(315, 28, 178, 35);
		desktopPane_3.add(u_id);

		u_name = new JTextField();
		u_name.setEnabled(false);
		u_name.setForeground(Color.WHITE);
		u_name.setColumns(10);
		u_name.setBackground(Color.WHITE);
		u_name.setBounds(315, 74, 178, 35);
		desktopPane_3.add(u_name);

		JLabel label_4 = new JLabel("Address");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(153, 118, 118, 35);
		desktopPane_3.add(label_4);

		u_add = new JTextField();
		u_add.setEnabled(false);
		u_add.setColumns(10);
		u_add.setBackground(Color.WHITE);
		u_add.setBounds(315, 120, 178, 35);
		desktopPane_3.add(u_add);

		u_con = new JTextField();
		u_con.setEnabled(false);
		u_con.setColumns(10);
		u_con.setBackground(Color.WHITE);
		u_con.setBounds(315, 166, 178, 35);
		desktopPane_3.add(u_con);

		u_u = new JTextField();
		u_u.setEnabled(false);
		u_u.setColumns(10);
		u_u.setBackground(Color.WHITE);
		u_u.setBounds(315, 212, 178, 35);
		desktopPane_3.add(u_u);

		u_p = new JTextField();
		u_p.setEnabled(false);
		u_p.setColumns(10);
		u_p.setBackground(Color.WHITE);
		u_p.setBounds(315, 258, 178, 35);
		desktopPane_3.add(u_p);

		JLabel label_5 = new JLabel("Password");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBounds(153, 256, 118, 35);
		desktopPane_3.add(label_5);

		JLabel label_6 = new JLabel("Username");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(153, 210, 118, 35);
		desktopPane_3.add(label_6);

		JLabel label_7 = new JLabel("Contact");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_7.setBounds(153, 164, 118, 35);
		desktopPane_3.add(label_7);

		final JLabel label_3 = new JLabel("");
		label_3.setBounds(119, 398, 408, 27);
		desktopPane_3.add(label_3);

		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(u_id.getText());

				if (LibDB.del(id) == 1) {
					setDis(true);

					String nm = u_name.getText();
					String addr = u_add.getText();
					int cont = Integer.parseInt(u_con.getText());
					String username = u_u.getText();
					String password = u_p.getText();

					if (LibDB.save(nm, id, addr, cont) == 0 && AdminDB.save(username, password) == 0) {
						label_3.setText("Librarian updated successfully!");
					}
				} else
					label_3.setText("Librarian does not exists!");
				clear();
			}

			public void setDis(boolean b) {
				u_name.setEnabled(b);
				u_add.setEnabled(b);
				u_con.setEnabled(b);
				u_u.setEnabled(b);
				u_p.setEnabled(b);
			}

			public void clear() {
				u_id.setText(null);
				u_name.setText(null);
				u_add.setText(null);
				u_con.setText(null);
				u_u.setText(null);
				u_p.setText(null);
			}
		});
		update.setBounds(246, 340, 130, 47);
		desktopPane_3.add(update);

		JLabel lblEnterIdTo = new JLabel("Enter id to update");
		lblEnterIdTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterIdTo.setBounds(153, 26, 130, 35);
		desktopPane_3.add(lblEnterIdTo);

		final JDesktopPane desktopPane_2 = new JDesktopPane();
		desktopPane_2.setBackground(Color.WHITE);
		tabbedPane.addTab("All Librarians", null, desktopPane_2, null);

		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String data[][] = null;
				String column[] = null;
				try {
					java.sql.Connection con = InitDb.getConnection();
					java.sql.PreparedStatement ps = con.prepareStatement("select * from librarian",
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
				desktopPane_2.add(sp);
			}
		});
		btnDisplay.setBounds(10, 11, 89, 23);
		desktopPane_2.add(btnDisplay);

		final JDesktopPane desktopPane_5 = new JDesktopPane();
		desktopPane_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Logins", null, desktopPane_5, null);

		JButton pass = new JButton("Display");
		pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String data[][] = null;
				String column[] = null;
				try {
					java.sql.Connection con = InitDb.getConnection();
					java.sql.PreparedStatement ps = con.prepareStatement("select * from admins",
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

				JTable table2 = new JTable(data, column);
				// table.setBounds(10, 50, 400, 400);
				// desktopPane_4.add(table);
				JScrollPane sp = new JScrollPane(table2);
				table2.setEnabled(false);
				sp.setBounds(10, 60, 721, 475);
				desktopPane_5.add(sp);
			}
		});
		pass.setBounds(10, 11, 89, 23);
		desktopPane_5.add(pass);
	}
}

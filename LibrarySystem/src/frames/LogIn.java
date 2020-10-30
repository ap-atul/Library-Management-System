package frames;

import java.awt.EventQueue;

import classes.*;
import threads.MultiThreading;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.SwingConstants;

public class LogIn {

	JFrame frame;
	static MultiThreading m;
	static Boolean var = false;
	Admin_Page a = new Admin_Page();
	LibChoice lp = new LibChoice();
	private JLabel login_info;
	private JPasswordField password;
	private JTextField username;
	static LogIn log_in;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					m = new MultiThreading();
					log_in = new LogIn();
					log_in.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Library Management System");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"C:\\Users\\AP_Atul Patare\\Desktop\\Wp\\Library System\\skullcandy_wallpaper_dark_by_antman1591.jpg"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		password = new JPasswordField();
		password.setBounds(35, 147, 165, 30);
		password.setColumns(10);
		frame.getContentPane().add(password);

		JButton admin_button = new JButton("Admin Login");
		admin_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usern = username.getText();
				@SuppressWarnings("deprecation")
				String pass = password.getText();

				if (AdminDB.login(usern, pass) == 1) {
					a.frame.setVisible(true);
					log_in.frame.setVisible(false);
				} else {
					login_info.setText("Invalid Credentials.... Try Again!");
				}
			}
		});
		admin_button.setBounds(250, 65, 123, 35);
		frame.getContentPane().add(admin_button);

		username = new JTextField();
		username.setColumns(10);
		username.setBounds(35, 67, 165, 30);
		frame.getContentPane().add(username);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(43, 42, 77, 14);
		frame.getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(43, 122, 77, 14);
		frame.getContentPane().add(lblPassword);

		JButton lib_button = new JButton("Librarian Login");
		lib_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usern = username.getText();
				@SuppressWarnings("deprecation")
				String pass = password.getText();

				if (AdminDB.login(usern, pass) == 1) {
					lp.frame.setVisible(true);
					log_in.frame.setVisible(false);
				} else {
					login_info.setText("Invalid Credentials.... Try Again!");
				}
			}
		});
		lib_button.setBounds(250, 145, 155, 35);
		frame.getContentPane().add(lib_button);

		login_info = new JLabel("");
		login_info.setHorizontalAlignment(SwingConstants.CENTER);
		login_info.setBounds(68, 210, 322, 14);
		frame.getContentPane().add(login_info);
	}
}

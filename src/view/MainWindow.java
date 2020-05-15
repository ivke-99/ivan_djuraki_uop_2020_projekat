package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import controller.LoginHandling;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class MainWindow {

	public JFrame LoginPage;
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.LoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		LoginPage = new JFrame();
		LoginPage.setTitle("Login Page");
		LoginPage.setBounds(100, 100, 321, 297);
		LoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LoginPage.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(117, 11, 60, 31);
		LoginPage.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Korisnicko Ime");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(20, 84, 105, 19);
		LoginPage.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lozinka");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(20, 114, 105, 19);
		LoginPage.getContentPane().add(lblNewLabel_1_1);
		
		txtUser = new JTextField();
		txtUser.setBounds(134, 85, 113, 20);
		LoginPage.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnUlogujMe = new JButton("Uloguj me");
		btnUlogujMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String username=txtUser.getText();
				@SuppressWarnings("deprecation")
				String password=txtPass.getText();
				try {
					controller.LoginHandling.CheckLogin(username, password);
					
				} catch (Exception input) {
					input.printStackTrace();
				}
				
				if (LoginHandling.trenutniKorisnik != null) {
				LoginPage.dispose();
				}
			}
		});
		btnUlogujMe.setBounds(86, 208, 118, 39);
		LoginPage.getContentPane().add(btnUlogujMe);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPass.setBounds(135, 115, 112, 19);
		LoginPage.getContentPane().add(txtPass);
	}
}

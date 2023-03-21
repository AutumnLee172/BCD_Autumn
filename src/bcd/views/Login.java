package bcd.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Label;
import java.awt.ComponentOrientation;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import bcd.hashing.Hasher;

public class Login {

	JFrame frame;
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private JPasswordField txtCPassword;
	private JLabel lblResult_Register;
	private JPasswordField txtPassword_Login;
	private JTextField txtUsername_Login;
	private JLabel lblResult_Login;

	public static final String MasterFolder = "master/";
	public static final String UserFile = MasterFolder + "user.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();

	}

	public void createFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (!checkFile(filePath)) {
			new File(MasterFolder).mkdir();
			file.createNewFile();
		}
	}

	public boolean checkFile(String filePath) throws IOException {
		File file = new File(filePath);
		return file.exists();
	}

	public void register() {
		String username = txtUsername.getText();
		@SuppressWarnings("deprecation")
		String password = txtPassword.getText();
		String Cpassword = txtCPassword.getText();
		byte[] salt = Hasher.generateSalt(16);
		String passwordhash = Hasher.sha256(password, salt);

		lblResult_Register.setVisible(true);

		if (username.trim().isEmpty()) {
			lblResult_Register.setText("Username is empty.");
		} else if (password.trim().isEmpty()) {
			lblResult_Register.setText("Password is empty.");
		} else if (!password.trim().equals(Cpassword.trim())) {
			lblResult_Register.setText("Mismatched Password");
		} else {
			try {
				createFile(UserFile);
				try (BufferedWriter writer = new BufferedWriter(new FileWriter(UserFile, true))) {
					writer.write(username + ":" + Base64.getEncoder().encodeToString(salt) + ":" + passwordhash);
					writer.newLine();
					System.out.println("Completed first time setup!");
					lblResult_Register.setText("Successfully setup");
					Login lg = new Login();
					lg.frame.setVisible(true);
					frame.dispose();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean authenticate() {
		File f = new File(UserFile);
		try (Scanner scanner = new Scanner(f)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(":");
				String storedUsername = parts[0];
				String storedSaltHex = parts[1];
				String storedHashHex = parts[2];

				if (storedUsername.equals(txtUsername_Login.getText())) {
					byte[] salt = Base64.getDecoder().decode(storedSaltHex);
					String computedHash = Hasher.sha256(txtPassword_Login.getText(), salt);
					System.out.println(computedHash);
					if (computedHash.equals(storedHashHex)) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 324);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel pnlSetup = new JPanel();
		pnlSetup.setBackground(new Color(255, 255, 255));
		pnlSetup.setBounds(0, 0, 434, 289);
		frame.getContentPane().add(pnlSetup);
		pnlSetup.setLayout(null);

		JLabel lblFirstTimeSetup = new JLabel("First Time Setup");
		lblFirstTimeSetup.setFont(new Font("Arial", Font.BOLD, 15));
		lblFirstTimeSetup.setBounds(10, 11, 145, 30);
		pnlSetup.add(lblFirstTimeSetup);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(160, 123, 210, 20);
		pnlSetup.add(txtPassword);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(61, 123, 78, 20);
		pnlSetup.add(lblPassword);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(61, 77, 78, 20);
		pnlSetup.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(160, 77, 210, 20);
		pnlSetup.add(txtUsername);
		txtUsername.setColumns(10);

		txtCPassword = new JPasswordField();
		txtCPassword.setBounds(160, 168, 210, 20);
		pnlSetup.add(txtCPassword);

		JLabel lblCPassword = new JLabel("Confirm Password");
		lblCPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCPassword.setBounds(39, 168, 100, 20);
		pnlSetup.add(lblCPassword);

		lblResult_Register = new JLabel("lblResult");
		lblResult_Register.setBounds(160, 200, 134, 14);
		pnlSetup.add(lblResult_Register);
		lblResult_Register.setVisible(false);

		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setBounds(161, 225, 89, 23);
		pnlSetup.add(btnConfirm);

		JPanel pnlLogin = new JPanel();
		pnlLogin.setBackground(new Color(255, 255, 255));
		pnlLogin.setBounds(0, 0, 434, 310);
		frame.getContentPane().add(pnlLogin);
		pnlLogin.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Arial", Font.BOLD, 15));
		lblLogin.setBounds(10, 11, 145, 30);
		pnlLogin.add(lblLogin);

		JLabel lblUsername_login = new JLabel("Username");
		lblUsername_login.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername_login.setBounds(61, 77, 78, 20);
		pnlLogin.add(lblUsername_login);

		txtUsername_Login = new JTextField();
		txtUsername_Login.setBounds(160, 77, 210, 20);
		pnlLogin.add(txtUsername_Login);
		txtUsername_Login.setColumns(10);

		JLabel lblPassword_Login = new JLabel("Password");
		lblPassword_Login.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword_Login.setBounds(61, 123, 78, 20);
		pnlLogin.add(lblPassword_Login);

		txtPassword_Login = new JPasswordField();
		txtPassword_Login.setBounds(160, 123, 210, 20);
		pnlLogin.add(txtPassword_Login);

		lblResult_Login = new JLabel("lblResult");
		lblResult_Login.setBounds(160, 200, 134, 14);
		pnlLogin.add(lblResult_Login);
		lblResult_Login.setVisible(false);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblResult_Login.setVisible(true);
				boolean IsAuthenticated = authenticate();
				if (IsAuthenticated) {
					lblResult_Login.setText("Successfully Logged In.");
					Dashboard dh;
					try {
						dh = new Dashboard();
						dh.frame.setVisible(true);
						frame.dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				} else {
					lblResult_Login.setText("Incorrect credentials. ");
				}
			}
		});
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(161, 225, 89, 23);
		pnlLogin.add(btnLogin);

		try {
			if (!checkFile(UserFile)) {
				pnlLogin.setVisible(false);
				pnlSetup.setVisible(true);
			} else {
				pnlLogin.setVisible(true);
				pnlSetup.setVisible(false);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

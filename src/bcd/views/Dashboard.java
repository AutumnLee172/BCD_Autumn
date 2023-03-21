package bcd.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import bcd.blockchain.Blockchain;
import bcd.signature.KeyGenerator;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Dashboard {

	JFrame frame;
	JPanel MainPanel;
	private JPanel pnlDashboard = new PnlDashboard();
	private JPanel AddBlock = new AddBlock();
	private JPanel AsymDecrypt = new AsymDecrypt();
	private final JButton btnLogout = new JButton("Logout");
	private final JButton btnRefresh = new JButton("Refresh");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Dashboard() throws IOException {
		initialize();
	}
	
	public void switchPanel(JPanel Panel) {
		pnlDashboard.setVisible(false);
		AddBlock.setVisible(false);
		AsymDecrypt.setVisible(false);
		
		Panel.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 746, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		pnlDashboard.setBackground(Color.WHITE);
		pnlDashboard.setBounds(121, 0, 592, 477);
		frame.getContentPane().add(pnlDashboard);
		pnlDashboard.setLayout(null);
		
		AddBlock.setBackground(Color.WHITE);
		AddBlock.setBounds(121, 0, 592, 477);
		frame.getContentPane().add(AddBlock);
		AddBlock.setLayout(null);
		AddBlock.setVisible(false);
		
		AsymDecrypt.setBackground(Color.WHITE);
		AsymDecrypt.setBounds(121, 0, 592, 477);
		frame.getContentPane().add(AsymDecrypt);
		AsymDecrypt.setLayout(null);
		AsymDecrypt.setVisible(false);
		
		JButton btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(pnlDashboard);
			}
		});
		btnDashboard.setForeground(Color.WHITE);
		btnDashboard.setBackground(new Color(102, 51, 255));
		btnDashboard.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnDashboard.setBounds(10, 24, 101, 23);
		btnDashboard.setFocusPainted(false);
		frame.getContentPane().add(btnDashboard);
		
		JButton btnNewBlock = new JButton("New Block");
		btnNewBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(AddBlock);
			}
		});
		btnNewBlock.setForeground(Color.WHITE);
		btnNewBlock.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnNewBlock.setBackground(new Color(102, 51, 255));
		btnNewBlock.setBounds(10, 58, 101, 23);
		btnNewBlock.setFocusPainted(false);
		frame.getContentPane().add(btnNewBlock);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(AsymDecrypt);
			}
		});
		btnDecrypt.setForeground(Color.WHITE);
		btnDecrypt.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnDecrypt.setFocusPainted(false);
		btnDecrypt.setBackground(new Color(102, 51, 255));
		btnDecrypt.setBounds(10, 92, 101, 23);
		frame.getContentPane().add(btnDecrypt);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg = new Login();
				lg.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnLogout.setFocusPainted(false);
		btnLogout.setBackground(new Color(102, 51, 255));
		btnLogout.setBounds(10, 126, 101, 23);
		
		if(!Blockchain.checkFile(KeyGenerator.PRIVATE_FILE) || !Blockchain.checkFile(KeyGenerator.PUBLIC_FILE)) {
			btnNewBlock.setEnabled(false);
			btnDecrypt.setEnabled(false);
		}
		
		frame.getContentPane().add(btnLogout);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard dh;
				try {
					dh = new Dashboard();
					dh.frame.setVisible(true);
					frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnRefresh.setFocusPainted(false);
		btnRefresh.setBackground(new Color(40, 167, 69));
		btnRefresh.setBounds(10, 454, 101, 23);
		btnRefresh.setBorderPainted(false);
		frame.getContentPane().add(btnRefresh);
	}
}

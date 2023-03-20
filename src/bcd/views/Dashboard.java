package bcd.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Dashboard {

	JFrame frame;
	JPanel MainPanel;
	private JPanel pnlDashboard = new PnlDashboard();
	private JPanel AddBlock = new AddBlock();
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
	 */
	public Dashboard() {
		initialize();
	}
	
	public void switchPanel(JPanel Panel) {
		pnlDashboard.setVisible(false);
		AddBlock.setVisible(false);
		
		Panel.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		btnNewBlock.setBounds(10, 69, 101, 23);
		btnNewBlock.setFocusPainted(false);
		frame.getContentPane().add(btnNewBlock);
		
		JButton btnDigitalSignature = new JButton("Digital Signature");
		btnDigitalSignature.setForeground(Color.WHITE);
		btnDigitalSignature.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnDigitalSignature.setFocusPainted(false);
		btnDigitalSignature.setBackground(new Color(102, 51, 255));
		btnDigitalSignature.setBounds(10, 113, 101, 23);
		frame.getContentPane().add(btnDigitalSignature);
	}
}

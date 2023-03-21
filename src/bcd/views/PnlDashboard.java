package bcd.views;

import javax.swing.JPanel;

import bcd.blockchain.Blockchain;
import bcd.signature.KeyAccess;
import bcd.signature.KeyGenerator;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.Color;

public class PnlDashboard extends JPanel {

	JLabel lblResult_Key;
	JLabel lblPublicKeyValue;
	JLabel lblPrivateKeyValue;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */

	public void showKeys(String KeyType) throws Exception {
		if (KeyType.equals("private")) {
			PrivateKey privatekey = KeyAccess.getPrivateKey(KeyGenerator.PRIVATE_FILE);
			String key = Base64.getEncoder().encodeToString(privatekey.getEncoded());
			lblPrivateKeyValue.setText(key);
		} else if (KeyType.equals("public")) {
			PublicKey publicKey = KeyAccess.getPublicKey(KeyGenerator.PUBLIC_FILE);
			String key = Base64.getEncoder().encodeToString(publicKey.getEncoded());
			lblPublicKeyValue.setText(key);
		}
	}
	
	public void hideKeys(JLabel label) {
		label.setText("*****************");
	}

	public PnlDashboard() {
		setLayout(null);

		JLabel lblPanel = new JLabel("Dashboard");
		lblPanel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPanel.setBounds(10, 11, 148, 38);
		add(lblPanel);

		JButton btnGenerateKey = new JButton("Generate Key Pair");
		btnGenerateKey.setBackground(Color.WHITE);
		btnGenerateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyGenerator kg = new KeyGenerator();
				try {
					lblResult_Key.setVisible(true);
					if (!Blockchain.checkFile(kg.PRIVATE_FILE) && !Blockchain.checkFile(kg.PUBLIC_FILE)) {
						kg.generate();
						lblResult_Key.setText("Successfully generated a key pair.");
					} else {
						lblResult_Key.setText("There is an existing key pair.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblResult_Key.setText("Error generating key pair.");
				}

			}
		});
		btnGenerateKey.setBounds(10, 263, 148, 31);
		add(btnGenerateKey);

		lblResult_Key = new JLabel("New label");
		lblResult_Key.setBounds(168, 266, 203, 24);
		add(lblResult_Key);

		JLabel lblPublicKey = new JLabel("Public Key");
		lblPublicKey.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPublicKey.setBounds(10, 110, 68, 24);
		add(lblPublicKey);

		JLabel lblPrivateKey = new JLabel("Private Key");
		lblPrivateKey.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPrivateKey.setBounds(10, 163, 73, 24);
		add(lblPrivateKey);

		lblPublicKeyValue = new JLabel("***********");
		lblPublicKeyValue.setFont(new Font("SansSerif", Font.BOLD, 11));
		lblPublicKeyValue.setBounds(10, 138, 361, 24);
		add(lblPublicKeyValue);

		lblPrivateKeyValue = new JLabel("***********");
		lblPrivateKeyValue.setFont(new Font("SansSerif", Font.BOLD, 11));
		lblPrivateKeyValue.setBounds(10, 190, 361, 24);
		add(lblPrivateKeyValue);

		JLabel lblNoteYouHave = new JLabel("Note: You have to generate a key pair to use more functions");
		lblNoteYouHave.setForeground(Color.RED);
		lblNoteYouHave.setFont(new Font("SansSerif", Font.PLAIN, 10));
		lblNoteYouHave.setBounds(10, 305, 361, 24);
		add(lblNoteYouHave);

		JLabel lblTitle = new JLabel("Welcome to the Logistic in the Blockchain World!");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTitle.setBounds(10, 61, 456, 38);
		add(lblTitle);

		JButton btnShowPublic = new JButton("Show");
		btnShowPublic.setBackground(Color.WHITE);
		btnShowPublic.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnShowPublic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch(btnShowPublic.getText()) {
					case "Show":
						showKeys("public");
						btnShowPublic.setText("Hide");
						break;
					case "Hide":
						hideKeys(lblPublicKeyValue);
						btnShowPublic.setText("Show");
						break;
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowPublic.setBounds(82, 112, 59, 22);
		btnShowPublic.setFocusPainted(false);
		add(btnShowPublic);

		JButton btnShowPrivate = new JButton("Show");
		btnShowPrivate.setBackground(Color.WHITE);
		btnShowPrivate.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnShowPrivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					switch(btnShowPrivate.getText()) {
					case "Show":
						showKeys("private");
						btnShowPrivate.setText("Hide");
						break;
					case "Hide":
						hideKeys(lblPrivateKeyValue);
						btnShowPrivate.setText("Show");
						break;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnShowPrivate.setBounds(82, 165, 59, 22);
		btnShowPrivate.setFocusPainted(false);
		add(btnShowPrivate);
		lblResult_Key.setVisible(false);

		try {
			if (!Blockchain.checkFile(KeyGenerator.PRIVATE_FILE) && !Blockchain.checkFile(KeyGenerator.PUBLIC_FILE)) {
				lblPublicKeyValue.setText("You do not have a key pair");
				lblPrivateKeyValue.setText("You do not have a key pair");
				lblPublicKeyValue.setForeground(Color.red);
				lblPrivateKeyValue.setForeground(Color.red);
				btnShowPublic.setEnabled(false);
				btnShowPrivate.setEnabled(false);
			} else {
				lblPublicKeyValue.setText("*****************");
				lblPrivateKeyValue.setText("****************");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

package bcd.views;

import javax.swing.JPanel;

import bcd.blockchain.Blockchain;
import bcd.signature.KeyGenerator;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class PnlDashboard extends JPanel {

	JLabel lblResult_Key;
	/**
	 * Create the panel.
	 */
	public PnlDashboard() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 148, 38);
		add(lblNewLabel);

		JButton btnGenerateKey = new JButton("Generate Key Pair");
		btnGenerateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KeyGenerator kg = new KeyGenerator();
				try {
					lblResult_Key.setVisible(true);
					if (!Blockchain.checkFile(kg.PRIVATE_FILE) && !Blockchain.checkFile(kg.PUBLIC_FILE)) {
						kg.generate();
						lblResult_Key.setText("Successfully generated a key pair.");
					}else {
						lblResult_Key.setText("There is an existing key pair.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					lblResult_Key.setText("Error generating key pair.");
				}

			}
		});
		btnGenerateKey.setBounds(432, 366, 148, 31);
		add(btnGenerateKey);
		
		lblResult_Key = new JLabel("New label");
		lblResult_Key.setBounds(432, 331, 148, 24);
		add(lblResult_Key);
		lblResult_Key.setVisible(false);

	}
}

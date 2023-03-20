package bcd.views;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PnlDashboard extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlDashboard() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setBounds(10, 11, 148, 38);
		add(lblNewLabel);

	}

}

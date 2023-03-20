package bcd.views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;

public class AddBlock extends JPanel {
	private JTextField txtDate;
	private JTextField txtDescription;
	private JTextField txtQuantity;
	private JTextField txtUnit;
	private JTextField txtWeight;
	private JTextField txtImport;
	private JTextField txtOrigin;
	private JTextField txtValue;
	private JTextField txtStatus;

	/**
	 * Create the panel.
	 */
	public AddBlock() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		JLabel lblTitle = new JLabel("New Logistic Transaction");
		lblTitle.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblTitle.setBounds(10, 11, 206, 36);
		add(lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 47, 505, 366);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblImport = new JLabel("Import/Domestic Block");
		lblImport.setBounds(10, 11, 171, 14);
		panel.add(lblImport);
		
		txtDate = new JTextField();
		txtDate.setBounds(110, 36, 152, 20);
		panel.add(txtDate);
		txtDate.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 36, 50, 20);
		panel.add(lblDate);
		
		JLabel lblItemDescription = new JLabel("Item Description");
		lblItemDescription.setBounds(10, 67, 91, 20);
		panel.add(lblItemDescription);
		
		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(110, 67, 152, 20);
		panel.add(txtDescription);
		
		JLabel lblQuanitity = new JLabel("Quanitity");
		lblQuanitity.setBounds(10, 97, 91, 20);
		panel.add(lblQuanitity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(110, 98, 152, 20);
		panel.add(txtQuantity);
		
		JLabel lblMeasureUnit = new JLabel("Measure Unit");
		lblMeasureUnit.setBounds(10, 128, 91, 20);
		panel.add(lblMeasureUnit);
		
		txtUnit = new JTextField();
		txtUnit.setColumns(10);
		txtUnit.setBounds(110, 129, 152, 20);
		panel.add(txtUnit);
		
		JLabel lblNetWeightkg = new JLabel("Net Weight (Kg)");
		lblNetWeightkg.setBounds(10, 159, 91, 20);
		panel.add(lblNetWeightkg);
		
		txtWeight = new JTextField();
		txtWeight.setColumns(10);
		txtWeight.setBounds(110, 160, 152, 20);
		panel.add(txtWeight);
		
		JLabel lblImportCountry = new JLabel("Import Country");
		lblImportCountry.setBounds(10, 190, 91, 20);
		panel.add(lblImportCountry);
		
		txtImport = new JTextField();
		txtImport.setColumns(10);
		txtImport.setBounds(110, 190, 152, 20);
		panel.add(txtImport);
		
		JLabel lblOriginCountry = new JLabel("Origin Country");
		lblOriginCountry.setBounds(10, 221, 91, 20);
		panel.add(lblOriginCountry);
		
		txtOrigin = new JTextField();
		txtOrigin.setColumns(10);
		txtOrigin.setBounds(110, 221, 152, 20);
		panel.add(txtOrigin);
		
		JLabel lblTotalValue = new JLabel("Total Value");
		lblTotalValue.setBounds(10, 252, 91, 20);
		panel.add(lblTotalValue);
		
		txtValue = new JTextField();
		txtValue.setColumns(10);
		txtValue.setBounds(110, 252, 152, 20);
		panel.add(txtValue);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 283, 91, 20);
		panel.add(lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setColumns(10);
		txtStatus.setBounds(110, 283, 152, 20);
		panel.add(txtStatus);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(406, 332, 89, 23);
		panel.add(btnCreate);

	}
}

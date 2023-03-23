package bcd.views;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import bcd.blockchain.Block;
import bcd.blockchain.Blockchain;
import bcd.blockchain.Transaction;
import bcd.signature.KeyGenerator;

import javax.swing.JSplitPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;

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
	private JTextField txtCustomerName;
	private JTextField txtSKU;
	private JTextField txtCustomerAddress;
	private JTextField txtReceiverAddress;
	private JTextField txtReceiverName;
	private JTextField txtReceiverContact;
	private JTextField txtSenderAddress;
	private JTextField txtSenderName;
	private JTextField txtContact;
	private JTextField txtTracking;
	private JLabel lblResult;

	public void createBlock() throws Exception {
		try {
			if(validateInput() && checkKeys()) {
			String trx1 = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s", txtDate.getText(), txtDescription.getText(),
					txtQuantity.getText(), txtUnit.getText(), txtWeight.getText(), txtImport.getText(),
					txtOrigin.getText(), txtValue.getText(), txtStatus.getText());
			Blockchain bc = new Blockchain();
			bc.genesis();
			Transaction tranxLst = new Transaction();
			tranxLst.add(trx1);
			tranxLst.SignandEncrypt();
			String previousHash = bc.get().getLast().getBlockHeader().getHash();
			Block b1 = new Block(previousHash);
			b1.setTranxLst(tranxLst);
			bc.nextBlock(b1);

			String trx2 = String.format("%s|%s|%s", txtCustomerName.getText(), txtCustomerAddress.getText(),
					txtSKU.getText());
			Transaction tranxLst2 = new Transaction();
			tranxLst2.add(trx2);
			tranxLst2.SignandEncrypt();
			String previousHash2 = bc.get().getLast().getBlockHeader().getHash();
			Block b2 = new Block(previousHash2);
			b2.setTranxLst(tranxLst2);
			bc.nextBlock(b2);

			String trx3 = String.format("%s|%s|%s|%s|%s|%s|%s", txtReceiverAddress.getText(), txtReceiverName.getText(),
					txtReceiverContact.getText(), txtSenderAddress.getText(), txtSenderName.getText(),
					txtContact.getText(), txtTracking.getText());
			Transaction tranxLst3 = new Transaction();
			tranxLst3.add(trx3);
			tranxLst3.SignandEncrypt();
			String previousHash3 = bc.get().getLast().getBlockHeader().getHash();
			Block b3 = new Block(previousHash3);
			b3.setTranxLst(tranxLst3);
			bc.nextBlock(b3);
			
			lblResult.setVisible(true);
			lblResult.setText("Successfully created the transaction");
			}
			else if(!checkKeys()) {
				lblResult.setVisible(true);
				lblResult.setText("Please generate your key pairs.");
			}
			else if(!validateInput()) {
				lblResult.setVisible(true);
				lblResult.setText("All fields are required.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean validateInput() {
		boolean isValid = false;
		if (!txtDate.getText().trim().isEmpty() && !txtDescription.getText().trim().isEmpty()
				&& !txtQuantity.getText().trim().isEmpty() && !txtUnit.getText().trim().isEmpty()
				&& !txtWeight.getText().trim().isEmpty() && !txtImport.getText().trim().isEmpty()
				&& !txtOrigin.getText().trim().isEmpty() && !txtValue.getText().trim().isEmpty()
				&& !txtStatus.getText().trim().isEmpty() && !txtCustomerName.getText().trim().isEmpty()
				&& !txtSKU.getText().trim().isEmpty() && !txtCustomerAddress.getText().trim().isEmpty()
				&& !txtReceiverAddress.getText().trim().isEmpty() && !txtReceiverName.getText().trim().isEmpty()
				&& !txtReceiverContact.getText().trim().isEmpty() && !txtSenderAddress.getText().trim().isEmpty()
				&& !txtSenderName.getText().trim().isEmpty() && !txtContact.getText().trim().isEmpty()
				&& !txtTracking.getText().trim().isEmpty()) {
			isValid = true;
		} else {
			isValid = false;
		}
		return isValid;
	}

	public boolean checkKeys() throws IOException {
		KeyGenerator kg = new KeyGenerator();
		if (Blockchain.checkFile(kg.PRIVATE_FILE) && Blockchain.checkFile(kg.PUBLIC_FILE)) {
			return true;
		}else {
			return false;
		}
	}
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
		panel.setBounds(10, 47, 581, 432);
		add(panel);
		panel.setLayout(null);

		JLabel lblImport = new JLabel("Import/Domestic Block");
		lblImport.setFont(new Font("Tahoma", Font.BOLD, 11));
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

		JLabel lblNetWeightkg = new JLabel("Net Weight");
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
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setBackground(new Color(40, 167, 69));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createBlock();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreate.setBounds(446, 401, 89, 23);
		btnCreate.setBorderPainted(false);
		panel.add(btnCreate);

		JLabel lblSupplyChainData = new JLabel("Supply Chain Data");
		lblSupplyChainData.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSupplyChainData.setBounds(282, 11, 125, 14);
		panel.add(lblSupplyChainData);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(282, 36, 91, 20);
		panel.add(lblCustomerName);

		txtCustomerName = new JTextField();
		txtCustomerName.setColumns(10);
		txtCustomerName.setBounds(383, 36, 152, 20);
		panel.add(txtCustomerName);

		JLabel lblAddress = new JLabel("Item SKU");
		lblAddress.setBounds(282, 97, 91, 20);
		panel.add(lblAddress);

		txtSKU = new JTextField();
		txtSKU.setColumns(10);
		txtSKU.setBounds(383, 97, 152, 20);
		panel.add(txtSKU);

		JLabel lblQuantity = new JLabel("Customer Address");
		lblQuantity.setBounds(282, 67, 91, 20);
		panel.add(lblQuantity);

		txtCustomerAddress = new JTextField();
		txtCustomerAddress.setColumns(10);
		txtCustomerAddress.setBounds(383, 67, 152, 20);
		panel.add(txtCustomerAddress);

		JLabel lblTrackingData = new JLabel("Tracking Data");
		lblTrackingData.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrackingData.setBounds(282, 131, 125, 14);
		panel.add(lblTrackingData);

		JLabel lblReceiverAddress = new JLabel("Receiver Address");
		lblReceiverAddress.setBounds(282, 159, 91, 20);
		panel.add(lblReceiverAddress);

		txtReceiverAddress = new JTextField();
		txtReceiverAddress.setColumns(10);
		txtReceiverAddress.setBounds(383, 159, 152, 20);
		panel.add(txtReceiverAddress);

		JLabel lblReceiverName = new JLabel("Receiver Name");
		lblReceiverName.setBounds(282, 190, 91, 20);
		panel.add(lblReceiverName);

		txtReceiverName = new JTextField();
		txtReceiverName.setColumns(10);
		txtReceiverName.setBounds(383, 190, 152, 20);
		panel.add(txtReceiverName);

		JLabel lblReceiverContact = new JLabel("Receiver Contact");
		lblReceiverContact.setBounds(282, 221, 91, 20);
		panel.add(lblReceiverContact);

		txtReceiverContact = new JTextField();
		txtReceiverContact.setColumns(10);
		txtReceiverContact.setBounds(383, 221, 152, 20);
		panel.add(txtReceiverContact);

		JLabel lblSenderName = new JLabel("Sender Name");
		lblSenderName.setBounds(282, 283, 91, 20);
		panel.add(lblSenderName);

		txtSenderName = new JTextField();
		txtSenderName.setColumns(10);
		txtSenderName.setBounds(383, 283, 152, 20);
		panel.add(txtSenderName);

		JLabel lblReceiverContact_1 = new JLabel("Sender Address");
		lblReceiverContact_1.setBounds(282, 252, 91, 20);
		panel.add(lblReceiverContact_1);

		txtSenderAddress = new JTextField();
		txtSenderAddress.setColumns(10);
		txtSenderAddress.setBounds(383, 252, 152, 20);
		panel.add(txtSenderAddress);

		JLabel lblSenderContact = new JLabel("Sender Contact");
		lblSenderContact.setBounds(282, 314, 91, 20);
		panel.add(lblSenderContact);

		txtContact = new JTextField();
		txtContact.setColumns(10);
		txtContact.setBounds(383, 314, 152, 20);
		panel.add(txtContact);

		JLabel lblTrackingNumber = new JLabel("Tracking Number");
		lblTrackingNumber.setBounds(282, 345, 91, 20);
		panel.add(lblTrackingNumber);

		txtTracking = new JTextField();
		txtTracking.setColumns(10);
		txtTracking.setBounds(383, 345, 152, 20);
		panel.add(txtTracking);
		
		lblResult = new JLabel("Result");
		lblResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResult.setBounds(383, 376, 152, 14);
		panel.add(lblResult);
		lblResult.setVisible(false);

	}
}

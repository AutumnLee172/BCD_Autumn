package bcd.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import bcd.blockchain.Block;
import bcd.blockchain.Blockchain;
import bcd.crypto.AsymmetricEncryption;
import bcd.signature.DigitalSignature;
import bcd.signature.KeyAccess;
import bcd.signature.KeyGenerator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AsymDecrypt extends JPanel {
	private JTextField txtSearch;
	private JTextField txtDate;
	private JTextField txtDescription;
	private JTextField txtQuantity;
	private JTextField txtMeasureUnit;
	private JTextField txtNetWeight;
	private JTextField txtImport;
	private JTextField txtOrigin;
	private JTextField txtTotal;
	private JTextField txtCustomer;
	private JTextField txtCustomerAddress;
	private JTextField txtSKU;
	private JTextField txtReceiverAddress;
	private JTextField txtReceiverName;
	private JTextField txtReceiverContact;
	private JTextField txtSenderAddress;
	private JTextField txtSenderName;
	private JTextField txtSenderContact;
	private JTextField txtTrackingNumber;
	private JLabel lblResult;
	private JTextField txtStatus;

	public void search() throws Exception {
		lblResult.setVisible(true);
		int searchID = 0;
		try {
			searchID = Integer.parseInt(txtSearch.getText());
		} catch (Exception e) {
			lblResult.setText("Invalid Block ID");
			return;
		}
		int remainder = searchID % 3;

		//to ensure that the search ID is always referring to the first block of a chain
		switch (remainder) {
		case 0:
			searchID -= 2;
			break;
		case 1:
			break;
		case 2:
			searchID -= 1;
			break;
		default:
			lblResult.setText("Invalid Input");
			break;
		}

		try {
			LinkedList<Block> searchBlocks = new LinkedList<Block>();
			searchBlocks = Blockchain.search(searchID);
			AsymmetricEncryption asymm = new AsymmetricEncryption();
			
			List<String> TranxList = null;
			
			 for (int i = 0 ; i < searchBlocks.size(); i ++)
		        {
		            TranxList = searchBlocks.get(i).getTransaction().getTranxLst();
		            String[] tranx = TranxList.get(0).toString().split(":");

		            String decrypted = asymm.decrypt(tranx[0], KeyAccess.getPrivateKey(KeyGenerator.PRIVATE_FILE));
		            DigitalSignature signature = new DigitalSignature();
		            Boolean verified = signature.verify(decrypted, tranx[1],
		    				KeyAccess.getPublicKey(KeyGenerator.PUBLIC_FILE));
		            if(verified) {
		            	 String[] items = decrypted.split("\\|");
		            	 switch (i) {
				    		case 0:
				    			txtDate.setText(items[0]);
				    			txtDescription.setText(items[1]);
				    			txtQuantity.setText(items[2]);
				    			txtMeasureUnit.setText(items[3]);
				    			txtNetWeight.setText(items[4]);
				    			txtImport.setText(items[5]);
				    			txtOrigin.setText(items[6]);
				    			txtTotal.setText(items[7]);	
				    			txtStatus.setText(items[8]);	
				    			break;
				    		case 1:
				    			txtCustomer.setText(items[0]);
				    			txtCustomerAddress.setText(items[1]);
				    			txtSKU.setText(items[2]);
				    			break;
				    		case 2:
				    			txtReceiverAddress.setText(items[0]);
				    			txtReceiverName.setText(items[1]);
				    			txtReceiverContact.setText(items[2]);
				    			txtSenderAddress.setText(items[3]);
				    			txtSenderName.setText(items[4]);
				    			txtSenderContact.setText(items[5]);
				    			txtTrackingNumber.setText(items[6]);
				    			lblResult.setText("Successfully decrypted.");
				    			break;
				    		default:
				    			lblResult.setText("Error displaying the result.");
				    			break;
				    		}
		            }else {
		            	lblResult.setText("Invalid Signature Verification");
		            }
		        }
			 if(TranxList == null) {
				 lblResult.setText("This block ID does not exist");
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Create the panel.
	 */
	public AsymDecrypt() {
		setLayout(null);

		JLabel lblTitle = new JLabel("Asymmetric Decryption");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(10, 11, 224, 41);
		add(lblTitle);

		txtSearch = new JTextField();
		txtSearch.setBounds(124, 55, 152, 20);
		add(txtSearch);
		txtSearch.setColumns(10);

		JLabel lblSearch = new JLabel("Enter a block ID");
		lblSearch.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSearch.setBounds(10, 58, 104, 14);
		add(lblSearch);

		JLabel lblImport = new JLabel("Import/Domestic Block");
		lblImport.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImport.setBounds(10, 94, 143, 25);
		add(lblImport);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 128, 46, 14);
		add(lblDate);

		txtDate = new JTextField();
		txtDate.setEnabled(false);
		txtDate.setColumns(10);
		txtDate.setBounds(124, 125, 167, 20);
		add(txtDate);

		JLabel lblItemDescription = new JLabel("Item Description");
		lblItemDescription.setBounds(10, 160, 104, 14);
		add(lblItemDescription);

		txtDescription = new JTextField();
		txtDescription.setEnabled(false);
		txtDescription.setColumns(10);
		txtDescription.setBounds(124, 155, 167, 20);
		add(txtDescription);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(10, 191, 104, 14);
		add(lblQuantity);

		txtQuantity = new JTextField();
		txtQuantity.setEnabled(false);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(124, 185, 167, 20);
		add(txtQuantity);

		JLabel lblMeasureUnit = new JLabel("Measure Unit");
		lblMeasureUnit.setBounds(10, 222, 104, 14);
		add(lblMeasureUnit);

		txtMeasureUnit = new JTextField();
		txtMeasureUnit.setEnabled(false);
		txtMeasureUnit.setColumns(10);
		txtMeasureUnit.setBounds(124, 215, 167, 20);
		add(txtMeasureUnit);

		JLabel lblNetWeight = new JLabel("Net Weight");
		lblNetWeight.setBounds(10, 249, 104, 14);
		add(lblNetWeight);

		txtNetWeight = new JTextField();
		txtNetWeight.setEnabled(false);
		txtNetWeight.setColumns(10);
		txtNetWeight.setBounds(124, 246, 167, 20);
		add(txtNetWeight);

		JLabel lblImportCountry = new JLabel("Import Country");
		lblImportCountry.setBounds(10, 280, 104, 14);
		add(lblImportCountry);

		txtImport = new JTextField();
		txtImport.setEnabled(false);
		txtImport.setColumns(10);
		txtImport.setBounds(124, 277, 167, 20);
		add(txtImport);

		JLabel lblOriginCountry = new JLabel("Origin Country");
		lblOriginCountry.setBounds(10, 311, 104, 14);
		add(lblOriginCountry);

		txtOrigin = new JTextField();
		txtOrigin.setEnabled(false);
		txtOrigin.setColumns(10);
		txtOrigin.setBounds(124, 308, 167, 20);
		add(txtOrigin);

		JLabel lblTotalValue = new JLabel("Total Value");
		lblTotalValue.setBounds(10, 342, 104, 14);
		add(lblTotalValue);

		txtTotal = new JTextField();
		txtTotal.setEnabled(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(124, 339, 167, 20);
		add(txtTotal);

		JLabel lblSupply = new JLabel("Supply Chain Data");
		lblSupply.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSupply.setBounds(314, 94, 143, 25);
		add(lblSupply);

		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(314, 128, 104, 14);
		add(lblCustomerName);

		txtCustomer = new JTextField();
		txtCustomer.setEnabled(false);
		txtCustomer.setColumns(10);
		txtCustomer.setBounds(410, 125, 167, 20);
		add(txtCustomer);

		JLabel lblCustomerAddress = new JLabel("Customer Address");
		lblCustomerAddress.setBounds(314, 160, 104, 14);
		add(lblCustomerAddress);

		txtCustomerAddress = new JTextField();
		txtCustomerAddress.setEnabled(false);
		txtCustomerAddress.setColumns(10);
		txtCustomerAddress.setBounds(410, 157, 167, 20);
		add(txtCustomerAddress);

		JLabel lblItemSku = new JLabel("Item SKU");
		lblItemSku.setBounds(314, 191, 104, 14);
		add(lblItemSku);

		txtSKU = new JTextField();
		txtSKU.setEnabled(false);
		txtSKU.setColumns(10);
		txtSKU.setBounds(410, 188, 167, 20);
		add(txtSKU);

		JLabel lblTrackingData = new JLabel("Tracking Data");
		lblTrackingData.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTrackingData.setBounds(314, 217, 143, 25);
		add(lblTrackingData);

		JLabel lblReceiverAddress = new JLabel("Receiver Address");
		lblReceiverAddress.setBounds(314, 249, 104, 14);
		add(lblReceiverAddress);

		txtReceiverAddress = new JTextField();
		txtReceiverAddress.setEnabled(false);
		txtReceiverAddress.setColumns(10);
		txtReceiverAddress.setBounds(410, 246, 167, 20);
		add(txtReceiverAddress);

		JLabel lblReceiverName = new JLabel("Receiver Name");
		lblReceiverName.setBounds(314, 280, 104, 14);
		add(lblReceiverName);

		txtReceiverName = new JTextField();
		txtReceiverName.setEnabled(false);
		txtReceiverName.setColumns(10);
		txtReceiverName.setBounds(410, 277, 167, 20);
		add(txtReceiverName);

		JLabel lblReceiverContact = new JLabel("Receiver Contact");
		lblReceiverContact.setBounds(314, 311, 104, 14);
		add(lblReceiverContact);

		txtReceiverContact = new JTextField();
		txtReceiverContact.setEnabled(false);
		txtReceiverContact.setColumns(10);
		txtReceiverContact.setBounds(410, 308, 167, 20);
		add(txtReceiverContact);

		JLabel lblSenderAddress = new JLabel("Sender Address");
		lblSenderAddress.setBounds(314, 342, 104, 14);
		add(lblSenderAddress);

		txtSenderAddress = new JTextField();
		txtSenderAddress.setEnabled(false);
		txtSenderAddress.setColumns(10);
		txtSenderAddress.setBounds(410, 339, 167, 20);
		add(txtSenderAddress);

		JLabel lblSenderName = new JLabel("Sender Name");
		lblSenderName.setBounds(314, 370, 104, 14);
		add(lblSenderName);

		txtSenderName = new JTextField();
		txtSenderName.setEnabled(false);
		txtSenderName.setColumns(10);
		txtSenderName.setBounds(410, 367, 167, 20);
		add(txtSenderName);

		txtSenderContact = new JTextField();
		txtSenderContact.setEnabled(false);
		txtSenderContact.setColumns(10);
		txtSenderContact.setBounds(410, 395, 167, 20);
		add(txtSenderContact);

		JLabel lblSenderContact = new JLabel("Sender Contact");
		lblSenderContact.setBounds(314, 398, 104, 14);
		add(lblSenderContact);

		JLabel lblTrackingNumber = new JLabel("Tracking Number");
		lblTrackingNumber.setBounds(314, 426, 104, 14);
		add(lblTrackingNumber);

		txtTrackingNumber = new JTextField();
		txtTrackingNumber.setEnabled(false);
		txtTrackingNumber.setColumns(10);
		txtTrackingNumber.setBounds(410, 423, 167, 20);
		add(txtTrackingNumber);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					search();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(286, 54, 89, 23);
		btnSearch.setFocusPainted(false);
		add(btnSearch);

		lblResult = new JLabel("Result");
		lblResult.setBounds(10, 83, 367, 14);
		add(lblResult);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 373, 104, 14);
		add(lblStatus);
		
		txtStatus = new JTextField();
		txtStatus.setEnabled(false);
		txtStatus.setColumns(10);
		txtStatus.setBounds(124, 370, 167, 20);
		add(txtStatus);
		lblResult.setVisible(false);

	}
}

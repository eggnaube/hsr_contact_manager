package views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.ContactValidator;

import domain.Contact;

public class ContactPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//Label
	private JLabel lblContact;
	private JLabel lblName;
	private JLabel lblFirstName;
	private JLabel lblEMail;
	private JLabel lblTelOffice;
	private JLabel lblTelMobil;
	private JLabel lblBirthDay;

	//textfield
	private JTextField txtName;
	private JTextField txtFirstName;
	private JTextField txtEMail;
	private JTextField txtTelOffice;
	private JTextField txtTelMobil;
	private JTextField txtBirthDay;

	private JLabel namesErrorLabel;
	private JLabel eMailErrorLabel;
	private JLabel telOfficeErrorLabel;
	private JLabel telMobileErrorLabel;
	private JLabel birthDayErrorLabel;

	private Contact contact;
	

	private final static ImageIcon errorIcon = new ImageIcon(ContactJFrame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif"));
	private FocusListener saveCecker =  new CheckSaveableFocusListener();



	public ContactPanel() {
		
		GridBagLayout jPanel3Layout = new GridBagLayout();
		setPreferredSize(new java.awt.Dimension(300, 212));
		jPanel3Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
		jPanel3Layout.rowHeights = new int[] {12, 12, 12, 12, 12, 12, 12, 12};
		jPanel3Layout.columnWeights = new double[] {0.1, 0.1};
		jPanel3Layout.columnWidths = new int[] {10, 10, 10,10,10};
		setLayout(jPanel3Layout);
		{
			lblContact = new JLabel();
			add(lblContact, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
			lblContact.setText("Contact");
		}
		{
			lblName = new JLabel();
			add(lblName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
			lblName.setText("Name:");
		}
		{
			lblFirstName = new JLabel();
			add(lblFirstName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
			lblFirstName.setText("First Name:");
		}
		{
			lblEMail = new JLabel();
			add(lblEMail, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
			lblEMail.setText("E-Mail:");
		}
		{
			lblTelOffice = new JLabel();
			add(lblTelOffice, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
			lblTelOffice.setText("TelOffice:");
		}		
		{
			lblTelMobil = new JLabel();
			add(lblTelMobil, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
			lblTelMobil.setText("Mobil:");
		}
		{
			lblBirthDay= new JLabel();
			add(lblBirthDay, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
			lblBirthDay.setText("Birth Day:");
		}
		{
			txtName = new JTextField();
			txtName.addFocusListener(saveCecker);
			add(txtName, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
			txtName.setEnabled(true);
		}
		{
			txtFirstName = new JTextField();
			txtFirstName.addFocusListener(saveCecker);
			add(txtFirstName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
			txtFirstName.setEnabled(true);
		}
		{
			txtEMail = new JTextField();
			txtEMail.addFocusListener(saveCecker);
			add(txtEMail, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
			txtEMail.setEnabled(true);
		}
		{
			txtTelOffice = new JTextField();
			txtTelOffice.addFocusListener(saveCecker);
			add(txtTelOffice, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
			txtTelOffice.setEnabled(true);
		}
		{
			txtTelMobil = new JTextField();
			txtTelMobil.addFocusListener(saveCecker);
			add(txtTelMobil, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
			txtTelMobil.setEnabled(true);
		}
		{
			txtBirthDay = new JTextField();
			txtBirthDay.addFocusListener(saveCecker);
			add(txtBirthDay, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
			txtBirthDay.setEnabled(true);
		}
		{
			namesErrorLabel = new JLabel(errorIcon);
			namesErrorLabel.setVisible(false);
			namesErrorLabel.setToolTipText("Vorname und Nachmame d�rfen nicht beide l sein");
			add(namesErrorLabel, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));

		}
		{
			namesErrorLabel = new JLabel(errorIcon);
			namesErrorLabel.setVisible(false);
			namesErrorLabel.setToolTipText("Vorname und Nachmame d�rfen nicht beide l sein");
			add(namesErrorLabel, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));

		}
		{
			eMailErrorLabel = new JLabel(errorIcon);
			eMailErrorLabel.setVisible(false);
			eMailErrorLabel.setToolTipText("E-Mail Address must be valid");
			add(eMailErrorLabel, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
		}
		{
			telOfficeErrorLabel = new JLabel(errorIcon);
			telOfficeErrorLabel.setVisible(false);
			telOfficeErrorLabel.setToolTipText("Office Tel must be valid");
			add(telOfficeErrorLabel, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
		}
		{
			telMobileErrorLabel = new JLabel(errorIcon);
			telMobileErrorLabel.setVisible(false);
			telMobileErrorLabel.setToolTipText("Mobil Tel must be valid");
			add(telMobileErrorLabel, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
		}					
		{
			birthDayErrorLabel = new JLabel(errorIcon);
			birthDayErrorLabel.setVisible(false);
			birthDayErrorLabel.setToolTipText("Mobil Tel must be valid");
			add(birthDayErrorLabel, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
		}			

	}



	public void setContact(Contact contact)
	{
		this.contact = contact;

		if (contact == null){

			txtName.setText("");
			txtFirstName.setText("");
			txtEMail.setText("");
			txtTelOffice.setText("");
			txtTelMobil.setText("");
			txtBirthDay.setText("");

		} 
		else {
			txtName.setText(contact.getName());
			txtFirstName.setText(contact.getFirstName());
			txtEMail.setText(contact.geteMail());
			txtTelOffice.setText(contact.getTelOffice());
			txtTelMobil.setText(contact.getTelMobil());
			txtBirthDay.setText(contact.getBirthDay());
		}
	}

	public Contact getContact()
	{
		contact.setName(txtName.getText());
		contact.setFirstName(txtFirstName.getText());
		contact.seteMail(txtEMail.getText());
		contact.setTelOffice(txtTelOffice.getText());
		contact.setTelMobil(txtTelMobil.getText());
		contact.setBirthDay(txtBirthDay.getText());

		return contact;
	}



	//validate
	private boolean hasAtLeastOneName () {
		if (txtName.getText().equals("") && txtFirstName.getText().equals("")){
			namesErrorLabel.setVisible(true);
			return false;
		}else{
			namesErrorLabel.setVisible(false);
			return true;			
		}
	}

	private boolean isValidEmail () {
		if (ContactValidator.isValidEmail(txtEMail.getText())) {
			eMailErrorLabel.setVisible(false);
			return true;
		}else{
			eMailErrorLabel.setVisible(true);
			return false;		
		}
	}


	private boolean validOffTelNr () {
		if (ContactValidator.isValidTelNr(txtTelOffice.getText())) {
			telOfficeErrorLabel.setVisible(false);
			return true;
		}else{
			telOfficeErrorLabel.setVisible(true);
			return false;		
		}
	}

	private boolean validMobileTelNr () {
		if (ContactValidator.isValidTelNr(txtTelMobil.getText())) {
			telMobileErrorLabel.setVisible(false);
			return true;
		}else{
			telMobileErrorLabel.setVisible(true);
			return false;		
		}
	}

	private boolean validBirthDate () {
		if (ContactValidator.isValidDate(txtBirthDay.getText())) {
			birthDayErrorLabel.setVisible(false);
			return true;
		}else{
			birthDayErrorLabel.setVisible(true);
			return false;		
		}
	}

	public boolean checkSaveable() {
		Boolean isOk = hasAtLeastOneName();
		isOk = isValidEmail() && isOk;
		isOk = validOffTelNr() && isOk;
		isOk = validMobileTelNr() && isOk;
		isOk = validBirthDate() && isOk;

		return isOk;

	}

	private class CheckSaveableFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent arg0) {
			checkSaveable();
		}	
	}

}

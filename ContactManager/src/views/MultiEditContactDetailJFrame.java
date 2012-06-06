package views;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;

import domain.Contact;


/**
*
* MultiEditToDoDetailJFrame:
* For use in App4 the Frame would not need to be observer as it is the only place that changes happen
* For use in App5 it is useful as changes can also happen in the detail panel.
* 
*/
public class MultiEditContactDetailJFrame extends javax.swing.JFrame implements Observer {
	
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
	
	
	private JButton cancelButton;
	private JButton saveButton;
	private JPanel jPanel1;
	private Contact contact;
	
	private static Dictionary<Contact, MultiEditContactDetailJFrame> editFramesDict 
		= new Hashtable<Contact, MultiEditContactDetailJFrame>();

	
	private MultiEditContactDetailJFrame() {
		super();
		initGUI();
	}	

	public static void editContact(Contact contact) {
		MultiEditContactDetailJFrame editFrame = editFramesDict.get(contact);
		if (editFrame==null){
			editFrame = new MultiEditContactDetailJFrame();
			editFrame.setContact(contact);
			editFramesDict.put(contact, editFrame);
		}
		editFrame.setVisible(true);
	}
	
	public static void closeContactEditor(Contact contact){
		MultiEditContactDetailJFrame editFrame = editFramesDict.get(contact);
		if (editFrame!=null){
			editFrame.setContact(null);
			editFramesDict.remove(contact);
			editFrame.dispose();		
		}
	}

	private void setContact(Contact contact) {
		if (contact !=null){
			contact.deleteObserver(this);
		}
		this.contact = contact;
		displayContact();
		if (contact !=null){
			contact.addObserver(this);
		}
	}

	private void displayContact () {
		if (contact==null){
			
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

	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			GridBagLayout thisLayout = new GridBagLayout();
			this.setTitle("JoDo: Edit ToDo");
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.5};
			thisLayout.columnWidths = new int[] {7, 7};
			getContentPane().setLayout(thisLayout);
			this.setMinimumSize(new java.awt.Dimension(300, 200));
			{
				lblContact = new JLabel();
				getContentPane().add(lblContact, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(4, 2, 2, 2), 0, 0));
				lblContact.setText("Contact");
			}
			{
				lblName = new JLabel();
				getContentPane().add(lblName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
				lblName.setText("Name:");
			}
			{
				lblFirstName = new JLabel();
				getContentPane().add(lblFirstName, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
				lblFirstName.setText("First Name:");
			}
			{
				lblEMail = new JLabel();
				getContentPane().add(lblEMail, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
				lblEMail.setText("E-Mail:");
			}
			{
				lblTelOffice = new JLabel();
				getContentPane().add(lblTelOffice, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
				lblTelOffice.setText("TelOffice:");
			}		
			{
				lblTelMobil = new JLabel();
				getContentPane().add(lblTelMobil, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
				lblTelMobil.setText("Mobil:");
			}
			{
				lblBirthDay= new JLabel();
				getContentPane().add(lblBirthDay, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
				lblBirthDay.setText("Birth Day:");
			}
			{
				txtName = new JTextField();
				getContentPane().add(txtName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
				txtName.setEnabled(true);
			}
			{
				txtFirstName = new JTextField();
				getContentPane().add(txtFirstName, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
				txtFirstName.setEnabled(true);
			}
			{
				txtEMail = new JTextField();
				getContentPane().add(txtEMail, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
				txtEMail.setEnabled(true);
			}
			{
				txtTelOffice = new JTextField();
				getContentPane().add(txtTelOffice, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
				txtTelOffice.setEnabled(true);
			}
			{
				txtTelMobil = new JTextField();
				getContentPane().add(txtTelMobil, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
				txtTelMobil.setEnabled(true);
			}
			{
				txtBirthDay = new JTextField();
				getContentPane().add(txtBirthDay, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
				txtBirthDay.setEnabled(true);
			}
			
			
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
				{
					saveButton = new JButton();
					jPanel1.add(saveButton);
					saveButton.setText("Save");
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							saveButtonActionPerformed(evt);
						}
					});
				}
				{
					cancelButton = new JButton();
					jPanel1.add(cancelButton);
					cancelButton.setText("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							cancelButtonActionPerformed(evt);
						}
					});
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		displayContact();
	}
	
	private void saveButtonActionPerformed(ActionEvent evt) {
		contact.startBulkUpdate();
		
		contact.setName(txtName.getText());
		contact.setFirstName(txtFirstName.getText());
		contact.seteMail(txtEMail.getText());
		contact.setTelOffice(txtTelOffice.getText());
		contact.setTelMobil(txtTelMobil.getText());
		contact.setBirthDay(txtBirthDay.getText());	
		
		contact.completeBulkUpdate();
		
		dispose();
	}
	
	private void cancelButtonActionPerformed(ActionEvent evt) {
		editFramesDict.remove(contact);
		contact.deleteObserver(this);
		contact=null;
		dispose();
	}

}

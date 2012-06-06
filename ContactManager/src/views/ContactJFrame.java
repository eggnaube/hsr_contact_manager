package views;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import viewModels.ContactListModel;

import domain.Contact;
import domain.ContactStore;


public class ContactJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JList<Contact> contactJList;
	private JScrollPane jScrollPane1;
	
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
	
	
	//button
	private JButton saveButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton newButton;

	private JPanel jPanel3;
	private JSplitPane jSplitPane1;
	private JPanel jPanel2;
	
	private ContactListModel contactJListModel;
	
	private ContactChangedObserver contactChangedObserver = new ContactChangedObserver();

	
	private ContactStore contactStore;
	private Contact contact;
	
		
	public ContactJFrame(ContactStore contactStore) {
		super();
		this.contactStore = contactStore;
		this.contactStore.addObserver(contactChangedObserver); //update detail if list changes
		contactJListModel = new ContactListModel(this.contactStore);
		initGUI();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jSplitPane1 = new JSplitPane();
				getContentPane().add(jSplitPane1, BorderLayout.CENTER);
				jSplitPane1.setPreferredSize(new java.awt.Dimension(392, 214));
				{
					jScrollPane1 = new JScrollPane();
					jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 212));
					{
						contactJList = new JList<Contact>();
						contactJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						jScrollPane1.setViewportView(contactJList);
						contactJList.setModel(contactJListModel);
						contactJList.setCellRenderer(new ContactListPanelCellRenderer());
						contactJList.addListSelectionListener(new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent evt) {
								updateListButtons(contactJList.getSelectedValue() != null);
								updateDetailPanel();
							}
						});
					}
				}
				{
					jPanel3 = new JPanel();
					GridBagLayout jPanel3Layout = new GridBagLayout();
					jSplitPane1.add(jPanel3, JSplitPane.RIGHT);
					jPanel3.setPreferredSize(new java.awt.Dimension(125, 212));
					jPanel3Layout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
					jPanel3Layout.rowHeights = new int[] {7, 7, 7, 7, 7, 7};
					jPanel3Layout.columnWeights = new double[] {0.1, 0.1};
					jPanel3Layout.columnWidths = new int[] {7, 7};
					jPanel3.setLayout(jPanel3Layout);
					{
						lblContact = new JLabel();
						jPanel3.add(lblContact, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(4, 2, 2, 2), 0, 0));
						lblContact.setText("Contact");
					}
					{
						lblName = new JLabel();
						jPanel3.add(lblName, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
						lblName.setText("Name:");
					}
					{
						lblFirstName = new JLabel();
						jPanel3.add(lblFirstName, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
						lblFirstName.setText("First Name:");
					}
					{
						lblEMail = new JLabel();
						jPanel3.add(lblEMail, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
						lblEMail.setText("E-Mail:");
					}
					{
						lblTelOffice = new JLabel();
						jPanel3.add(lblTelOffice, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
						lblTelOffice.setText("TelOffice:");
					}		
					{
						lblTelMobil = new JLabel();
						jPanel3.add(lblTelMobil, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
						lblTelMobil.setText("Mobil:");
					}
					{
						lblBirthDay= new JLabel();
						jPanel3.add(lblBirthDay, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.VERTICAL, new Insets(4, 2, 2, 2), 0, 0));
						lblBirthDay.setText("Birth Day:");
					}
					{
						txtName = new JTextField();
						jPanel3.add(txtName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
						txtName.setEnabled(true);
					}
					{
						txtFirstName = new JTextField();
						jPanel3.add(txtFirstName, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
						txtFirstName.setEnabled(true);
					}
					{
						txtEMail = new JTextField();
						jPanel3.add(txtEMail, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
						txtEMail.setEnabled(true);
					}
					{
						txtTelOffice = new JTextField();
						jPanel3.add(txtTelOffice, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
						txtTelOffice.setEnabled(true);
					}
					{
						txtTelMobil = new JTextField();
						jPanel3.add(txtTelMobil, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
						txtTelMobil.setEnabled(true);
					}
					{
						txtBirthDay = new JTextField();
						jPanel3.add(txtBirthDay, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(4, 2, 2, 2), 0, 0));
						txtBirthDay.setEnabled(true);
					}
					{
						saveButton = new JButton();
						jPanel3.add(saveButton, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
						saveButton.setText("Save");
						saveButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								saveButtonActionPerformed((Contact)contactJList.getSelectedValue());
							}
						});
					}
				}
			}
			{
				jPanel2 = new JPanel();
				FlowLayout jPanel2Layout = new FlowLayout();
				jPanel2Layout.setAlignment(FlowLayout.LEFT);
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				{
					editButton = new JButton();
					jPanel2.add(editButton);
					editButton.setText("Edit");
					editButton.setPreferredSize(new java.awt.Dimension(100, 21));
					editButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							editButtonActionPerformed(evt);
						}
					});
				}
				{
					newButton = new JButton();
					jPanel2.add(newButton);
					newButton.setText("New");
					newButton.setPreferredSize(new java.awt.Dimension(100, 21));
					newButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							newButtonActionPerformed(evt);
						}
					});
				}
				{
					deleteButton = new JButton();
					jPanel2.add(deleteButton);
					deleteButton.setText("Delete");
					deleteButton.setPreferredSize(new java.awt.Dimension(100, 21));
					deleteButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							deleteButtonActionPerformed(evt);
						}
					});
				}
			}
			updateListButtons(false);
			pack();
			this.setSize(452, 305);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateListButtons(boolean itemIsSelected) {
		editButton.setEnabled(itemIsSelected);
		deleteButton.setEnabled(itemIsSelected);
	}
	
	private void updateDetailPanel() {
		if (contact != null) {
			contact.deleteObserver(contactChangedObserver);
			contact=null;
		}
		contact = (Contact) contactJList.getSelectedValue();
		if (contact == null){
			
			txtName.setText("");
			txtFirstName.setText("");
			txtEMail.setText("");
			txtTelOffice.setText("");
			txtTelMobil.setText("");
			txtBirthDay.setText("");
			
		} else {
			
			contact.addObserver(contactChangedObserver);
			
			txtName.setText(contact.getName());
			txtFirstName.setText(contact.getFirstName());
			txtEMail.setText(contact.geteMail());
			txtTelOffice.setText(contact.getTelOffice());
			txtTelMobil.setText(contact.getTelMobil());
			txtBirthDay.setText(contact.getBirthDay());
	
		}
		contactJList.repaint();
		updateListButtons(contactJList.getSelectedValue() != null);
	}
	
	private void deleteButtonActionPerformed(ActionEvent evt) {
		int selIndex = contactJList.getSelectedIndex();
		MultiEditContactDetailJFrame.closeContactEditor(contactStore.getContactAt(selIndex));
		contactStore.removeContactAt(selIndex);
		contactJList.setSelectedIndex(Math.min(selIndex, contactJListModel.getSize()-1));
		updateDetailPanel();
	}
	
	private void editButtonActionPerformed(ActionEvent evt) {
		Contact contact = (Contact)contactJList.getSelectedValue();
		MultiEditContactDetailJFrame.editContact(contact);
	}
	
	private void newButtonActionPerformed(ActionEvent evt) {
		//TODO
	}
	
	private void saveButtonActionPerformed(Contact contact) {
		contact.startBulkUpdate();
		
		contact.setName(txtName.getText());
		contact.setFirstName(txtFirstName.getText());
		contact.seteMail(txtEMail.getText());
		contact.setTelOffice(txtTelOffice.getText());
		contact.setTelMobil(txtTelMobil.getText());
		contact.setBirthDay(txtBirthDay.getText());	
		
		contact.completeBulkUpdate();		
	}

	private class ContactChangedObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			updateDetailPanel();			
		}
		
	}
}

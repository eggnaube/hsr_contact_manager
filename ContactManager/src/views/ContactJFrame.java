package views;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
	
	//button
	private JButton saveButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton newButton;

	private ContactPanel jPanel3;
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
				jSplitPane1.setPreferredSize(new java.awt.Dimension(500, 300));
				{
					jScrollPane1 = new JScrollPane();
					jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 212));
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
					jPanel3 = new ContactPanel();
					jSplitPane1.add(jPanel3, JSplitPane.RIGHT);
					{
						saveButton = new JButton();
						jPanel3.add(saveButton, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
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
			this.setSize(1000, 500);
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
		
		if(contact !=null )
			contact.addObserver(contactChangedObserver);
		
		jPanel3.setContact(contact);
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
		Contact contact = new Contact();
		contact.addObserver(contactChangedObserver);
		contactStore.addContact(contact);
		int index = contactJListModel.getSize()-1;
		contactJList.setSelectedIndex(index);
		contactJListModel.propagateUpdate(index);
		updateDetailPanel();
	}
	
	private void saveButtonActionPerformed(Contact contact) {
		contact.startBulkUpdate();
		contact = jPanel3.getContact();
		contact.completeBulkUpdate();		
	}

	private class ContactChangedObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			updateDetailPanel();			
		}
		
	}
}


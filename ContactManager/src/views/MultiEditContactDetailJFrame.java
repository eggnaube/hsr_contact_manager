package views;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.WindowConstants;

import domain.Contact;
import java.awt.Dimension;

public class MultiEditContactDetailJFrame extends javax.swing.JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	
	private ContactPanel jPanel3;
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
		jPanel3.setContact(this.contact);
		
		if (contact !=null){
			contact.addObserver(this);
		}
	}


	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			BorderLayout thisLayout = new BorderLayout();
			this.setTitle("JoDo: Edit ToDo");
			getContentPane().setLayout(thisLayout);
			this.setMinimumSize(new Dimension(400, 400));
			jPanel3 = new ContactPanel();
			getContentPane().add(jPanel3, BorderLayout.CENTER);
			
			
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1,BorderLayout.SOUTH);
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
		jPanel3.setContact(contact);
	}
	
	private void saveButtonActionPerformed(ActionEvent evt) {
		contact.startBulkUpdate();
		
		contact = jPanel3.getContact();
		
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

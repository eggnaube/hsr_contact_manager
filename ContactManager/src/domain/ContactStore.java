package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ContactStore extends Observable implements Observer {
	
	private List<Contact> contactList = new ArrayList<Contact>();
	
	public void addContact(String name, String firstName, String eMail, String telOffice, String telMobil, String birthDay){
		Contact contact = new Contact(name, firstName, eMail, telOffice, telMobil, birthDay);
		contact.addObserver(this);
		contactList.add(contact);
		doNotify();
	}
	
	public Contact getContactAt(int index){
		if (contactList.size() <= index){
			return null;
		}
		return contactList.get(index);
	}

	public void removeContactAt(int index){
		Contact contact = contactList.get(index);
		contact.deleteObserver(this);
		contactList.remove(index);
		doNotify();
	}

	public boolean removeContact(Contact contact){
		contact.deleteObserver(this);
		boolean succeeded = contactList.remove(contact);
		doNotify();
		return succeeded;
	}

	public int getLength(){
		return contactList.size();
	}
	
	public int indexOf (Contact contact) {
		return contactList.indexOf(contact);		
	}
	
	private void doNotify() {
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers(arg0);
	}

}

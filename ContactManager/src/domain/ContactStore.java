package domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class ContactStore extends Observable implements Observer {

	private List<Contact> contactList;


	public ContactStore() {
		contactList = readList();
	}

	public void addContact(String name, String firstName, String eMail, String telOffice, String telMobil, String birthDay){
		Contact contact = new Contact(name, firstName, eMail, telOffice, telMobil, birthDay);
		contact.addObserver(this);
		contactList.add(contact);
		doNotify();
		saveList(contactList);
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
		saveList(contactList);
	}

	public boolean removeContact(Contact contact){
		contact.deleteObserver(this);
		boolean succeeded = contactList.remove(contact);
		doNotify();
		saveList(contactList);
		return succeeded;
	}
	
	public boolean addContact(Contact contact) {
		contact.addObserver(this);
		boolean succeeded = contactList.add(contact);
		doNotify();
		saveList(contactList);
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
		saveList(contactList);
	}

	private List<Contact> readList()
	{
		List<Contact> contactList = null;
		try
		{
			FileInputStream saveFile = new FileInputStream("store.sav");
			ObjectInputStream restore = new ObjectInputStream(saveFile);
			contactList = (List<Contact>) restore.readObject();
		}
		catch(Exception e)
		{
			contactList = new ArrayList<Contact>();
		}

		return contactList;

	}

	private void saveList(final List<Contact> list)
	{
		try
		{
			ObjectOutputStream  save = new ObjectOutputStream(new FileOutputStream("store.sav"));
			save.writeObject(list);
			save.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}
		finally{

		}
	}

}

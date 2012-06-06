package viewModels;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import domain.Contact;
import domain.ContactStore;



public class ContactListModel extends AbstractListModel implements Observer {
	
	private static final long serialVersionUID = 1L;
	ContactStore contactStore;
	
	public ContactListModel(ContactStore contactStore){
		this.contactStore = contactStore;
		contactStore.addObserver(this);
	}
	
	public void propagateUpdate(int pos) {
		fireContentsChanged(this, pos, pos);
	}

	@Override
	public void update(Observable obj, Object arg1) {
		if (obj instanceof Contact){
			int pos = contactStore.indexOf((Contact)obj);
			fireContentsChanged(this, pos, pos);						
		}
		if (obj instanceof Contact){
			fireContentsChanged(this, 0, contactStore.getLength());
		}
	}

	@Override
	public Object getElementAt(int index) {
		return contactStore.getContactAt(index);
	}

	@Override
	public int getSize() {
		return contactStore.getLength();
	}

}

package application;

import javax.swing.SwingUtilities;

import views.ContactJFrame;
import domain.ContactStore;

public class Application {


	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Create Model
				ContactStore contactStore = new ContactStore();
				contactStore.addContact("Müller", "Hans", "test@testmail.com", "0121", "654556", "12.12.2000");
				//Show two Views: Editing in Detail Frames; Fully Synchronized
				ContactJFrame f1 = new ContactJFrame(contactStore);
				ContactJFrame f2 = new ContactJFrame(contactStore);
				
				f1.setLocation(50, 100);
				f2.setLocation(100, 150);
			}
		});
	}

}

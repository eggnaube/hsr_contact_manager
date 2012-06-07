package domain;

import java.io.Serializable;
import java.util.Observable;

public class Contact extends Observable implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean isBulkUpdating = false;
	
	private String name="";
	private String firstName="";
	private String eMail="";
	private String telOffice="";
	private String telMobil="";
	private String birthDay="";
	
	public Contact()
	{}

	public Contact(String name, String firstName, String eMail, String telOffice, String telMobil, String birthDay) {
		setName(name);
		setFirstName(firstName);
		seteMail(eMail);
		setTelOffice(telOffice);
		setTelMobil(telMobil);
		setBirthDay(birthDay);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		if (newName!=null && !newName.equals(name)){
			name = newName;
			doNotify();
		}
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String newFirstName) {
		if (newFirstName!=null && !newFirstName.equals(firstName)){
			firstName = newFirstName;
			doNotify();
		}
	}
	
	public String geteMail() {
		return eMail;
	}
	
	public void seteMail(String newEMail) {
		if (newEMail!=null && !newEMail.equals(eMail)){
			eMail = newEMail;
			doNotify();
		}
	}
	
	public String getTelOffice() {
		return telOffice;
	}
	
	public void setTelOffice(String newTelOffice) {
		if (newTelOffice!=null && !newTelOffice.equals(telOffice)){
			telOffice = newTelOffice;
			doNotify();
		}
	}
	
	public String getTelMobil() {
		return telMobil;
	}
	
	public void setTelMobil(String newTelMobil) {
		if (newTelMobil != null && !newTelMobil.equals(telMobil)){
			telMobil = newTelMobil;
			doNotify();
		}
	}
	
	public String getBirthDay() {
		return birthDay;
	}
	
	public void setBirthDay(String newBirthDay) {
		if (newBirthDay != null && !newBirthDay.equals(birthDay)){
			birthDay = newBirthDay;
			doNotify();
		}
	}
	
	private void doNotify() {
		if (!isBulkUpdating) {
			setChanged();
			notifyObservers();
		}
	}
	
	/*
	 * stops the sending notifications for bulk updates
	 */
	public void startBulkUpdate() {
		isBulkUpdating=true;
	}

	/*
	 * resumes the sending notifications
	 */
	public void completeBulkUpdate() {
		isBulkUpdating=false;
		doNotify();
	}

}

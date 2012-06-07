package guiUtil;

import util.ContactValidator;

public class ErrorTextFieldEmailValidator implements IErrorTextFieldValidator {

	@Override
	public boolean validate(String currentText) {
		return ContactValidator.isValidEmail(currentText);
	}

}

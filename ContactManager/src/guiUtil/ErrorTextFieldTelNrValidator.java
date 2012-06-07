package guiUtil;

import util.ContactValidator;

public class ErrorTextFieldTelNrValidator implements IErrorTextFieldValidator {

	@Override
	public boolean validate(String currentText) {
		return ContactValidator.isValidTelNr(currentText);
	}

}

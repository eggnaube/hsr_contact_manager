package guiUtil;

import util.ContactValidator;

public class ErrorTextFieldDateValidator implements IErrorTextFieldValidator {

	@Override
	public boolean validate(String currentText) {
		return ContactValidator.isValidDate(currentText);
	}

}

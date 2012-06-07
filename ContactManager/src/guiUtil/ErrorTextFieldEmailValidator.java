package guiUtil;

import util.Validator;

public class ErrorTextFieldEmailValidator implements IErrorTextFieldValidator {

	@Override
	public boolean validate(String currentText) {
		return Validator.isValidEmail(currentText);
	}

}

package guiUtil;

import util.Validator;

public class ErrorTextFieldDateValidator implements IErrorTextFieldValidator {

	@Override
	public boolean validate(String currentText) {
		return Validator.isValidDate(currentText);
	}

}

package guiUtil;

import util.Validator;

public class ErrorTextFieldTelNrValidator implements IErrorTextFieldValidator {

	@Override
	public boolean validate(String currentText) {
		return Validator.isValidTelNr(currentText);
	}

}

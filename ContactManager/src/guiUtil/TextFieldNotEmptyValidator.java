package guiUtil;

public class TextFieldNotEmptyValidator implements IErrorTextFieldValidator {

	@Override
	public boolean validate(String currentText) {
		return ! currentText.equals("");
	}

}

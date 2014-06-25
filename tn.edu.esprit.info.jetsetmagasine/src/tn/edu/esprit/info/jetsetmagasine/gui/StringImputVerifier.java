package tn.edu.esprit.info.jetsetmagasine.gui;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class StringImputVerifier extends InputVerifier {

	String value;

	public StringImputVerifier() {
		//this.value = value;
	}

	@Override
	public boolean verify(JComponent input) {

		JTextField textField = (JTextField) input;
		try {

			Float.parseFloat(textField.getText());
			return true;
		} catch (Exception ee) {
			textField.setText("");
			return false;
		}
	}
}

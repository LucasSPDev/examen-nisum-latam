package com.nisum.examen.validation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("passwordValidator")
public class PasswordValidationStrategy implements ValidationStrategy {

	@Value("${regex.password}")
	private String passwordRegex;

	@Override
	public boolean isValid(String input) {
		return Pattern.matches(passwordRegex, input);
	}
}
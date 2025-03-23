package com.nisum.examen.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("emailValidator")
public class EmailValidationStrategy implements ValidationStrategy {

    @Value("${regex.email}")
    private String emailRegex;

    @Override
    public boolean isValid(String input) {
        return Pattern.matches(emailRegex, input);
    }
}
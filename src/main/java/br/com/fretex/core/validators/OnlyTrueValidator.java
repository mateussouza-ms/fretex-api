package br.com.fretex.core.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.fretex.core.anotations.OnlyTrue;

public class OnlyTrueValidator implements ConstraintValidator<OnlyTrue, Boolean> {

	@Override
	public boolean isValid(Boolean value, ConstraintValidatorContext context) {
		if (value.equals(true)) {
			return true;
		}

		return false;
	}

}

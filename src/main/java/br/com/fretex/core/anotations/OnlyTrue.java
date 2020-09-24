package br.com.fretex.core.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.fretex.core.validators.OnlyTrueValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnlyTrueValidator.class)
public @interface OnlyTrue {

	String message() default "é permitido somente o valor 'true'";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	boolean value() default false;
}

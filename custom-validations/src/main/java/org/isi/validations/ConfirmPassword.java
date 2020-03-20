package org.isi.validations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ConfirmPasswordConstrainValidator.class)
@Documented
public @interface ConfirmPassword {
	String password();
    String confirm();
    String message() default "{message.key}";
    
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

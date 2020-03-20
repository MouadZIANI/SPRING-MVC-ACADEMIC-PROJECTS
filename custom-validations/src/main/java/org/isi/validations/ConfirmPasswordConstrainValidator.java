package org.isi.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class ConfirmPasswordConstrainValidator implements ConstraintValidator<ConfirmPassword, Object> {
	   
	private String password;
	private String confirm;
	   
   public void initialize(ConfirmPassword constraint) {
      this.password = constraint.password();
      this.confirm = constraint.confirm();
   }
   
   public boolean isValid(Object obj, ConstraintValidatorContext context) {
      try {
    	 String password = (String) getFieldValue(obj, this.password);
    	 String conferm =(String) getFieldValue(obj, this.confirm);
      
         if(password.equals(conferm)){
            return true;
         }
      } catch (Exception e) {
         return false;
      }
      return false;
   }
   
   private Object getFieldValue(Object object, String fieldName) throws Exception {
      Class<?> clazz = object.getClass();
      Field field = clazz.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field.get(object);
   }
}


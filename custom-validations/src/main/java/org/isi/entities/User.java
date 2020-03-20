package org.isi.entities;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.isi.validations.ConfirmPassword;

@ConfirmPassword(password = "password", confirm = "confirmPassword", message = "The npassword and confirmation password do not match")
public class User {
	
	@NotBlank(message="is required")
	@Min(value = 5 , message = "Username must contain at least 5 characters")
	private String userName;
	
	@NotBlank(message="is required")
	@Email(message = "The e-mail address is not a well-known role account.")
    private String email;
	
	@Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})" , message = "password's form incorect")
    private String password;
	
	@NotBlank(message="is required")
    private String confirmPassword;
    
	public User() {}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}

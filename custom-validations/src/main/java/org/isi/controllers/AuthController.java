package org.isi.controllers;

import javax.validation.Valid;

import org.isi.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
	
	@RequestMapping("/")
	public String showForm(Model themMdel) {
		themMdel.addAttribute("user", new User());
		return "auth/register";
	}
	
	@RequestMapping("/register")
	public String showResult(@Valid @ModelAttribute("user") User user,
							 BindingResult theBindingResult ) {
		if(theBindingResult.hasErrors()) {
			return "auth/register";
		} else {
			return "auth/success";
		}
	}

}

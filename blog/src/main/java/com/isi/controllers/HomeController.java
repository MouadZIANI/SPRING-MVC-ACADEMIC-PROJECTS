package com.isi.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isi.entities.Role;
import com.isi.entities.User;
import com.isi.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	private List<Role> usersRoles = new ArrayList<>();
	private User Nuser;
	
	@GetMapping("/")
	public String index(User user, Model model) {
		model.addAttribute("user", user);
		return "index"; 
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession http) {
		
		if(result.hasErrors()){
			model.addAttribute("user", user);
            return "index";
        }else {
        	Nuser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        	if(Nuser == null) {
        		return "redirect:/";
        	}
        	usersRoles = userService.findRoleByUserID(Nuser.getId());
        	usersRoles.forEach(el -> {
        		System.out.println(el.getRole());
        	});
        	http.setAttribute("ActiveUserRoles", usersRoles);
        }
		return "redirect:/article/";
	}
	
	@RequestMapping("/user/logout/")
	public String logout(HttpSession http) {
		http.removeAttribute("ActiveUserRoles");
		System.out.println(http.getAttribute("ActiveUserRoles"));
		return "redirect:/";
	}
}

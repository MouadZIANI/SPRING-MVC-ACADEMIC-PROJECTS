package com.isi.controllers;


import com.isi.entities.User;
import com.isi.exceptions.ResourceNotFoundException;
import com.isi.services.ArticleService;
import com.isi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ArticleService articleService;

    @GetMapping(value = {"/","/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model)
    {
            Page<User> pages = userService.getAllUsers(id, 1, "id");
            model.addAttribute("pageable", pages);
        return "user/list";
    }


    @GetMapping("/add")
    public String adminadd(ModelMap model,User user) {
            model.addAttribute("user", user);
            model.addAttribute("articles", articleService.getAllArticles());
       return "user/add";
    }

    @GetMapping("/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("user", userService.findById(id));
        return "user/add";
    }

    @PostMapping("/save")
    public String adminsaveUser(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) throws ResourceNotFoundException {
    	if(result.hasErrors()){
            model.addAttribute("user",user);
            model.addAttribute("articles", articleService.getAllArticles());
            return "user/add";
        }
        userService.save(user);
        return "redirect:/user/";
    }

    @GetMapping("/delete/{page}/{id}")
    public String admindelete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        userService.deleteById(id);
        return "redirect:/user/page/"+page;
    }






}

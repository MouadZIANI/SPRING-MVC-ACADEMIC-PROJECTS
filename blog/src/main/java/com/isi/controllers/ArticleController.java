package com.isi.controllers;


import com.isi.converter.TagFormatter;
import com.isi.entities.Article;
import com.isi.entities.Tag;
import com.isi.entities.User;
import com.isi.exceptions.ResourceNotFoundException;
import com.isi.services.ArticleService;
import com.isi.services.TagService;
import com.isi.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/article"})
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ConversionService conversionService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "tagList",
                new TagFormatter(List.class));
    }


    @GetMapping(value = {"/","/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model)
    {
            Page<Article> pages = articleService.getAllArticles(id, 1, "id");
            model.addAttribute("pageable", pages);
        return "article/home";
    }

    @RequestMapping("/view/{id}")
    public String view(@PathVariable("id") long id,ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("article",articleService.findById(id));
        return "article/view";
    }


    @GetMapping("/add")
    public String adminadd(ModelMap model,Article article) {
            model.addAttribute("tags", tagService.getAllTags());
            model.addAttribute("article", article);
            model.addAttribute("users", userService.getAllUsers());
       return "article/add";
    }

    @GetMapping("/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        Article article=articleService.findByIdWithTags(id);
        List<Tag> tags=tagService.getAllTags();
        List<User> users= userService.getAllUsers();
        
        tags.forEach(e->{
             article.getTagList().forEach(t->{
                 if(e.getId() ==t.getId()){
                     e.setUsed(true);
                 }
             });
        });
        
        users.forEach(user->{
        	if(user.getId() == article.getUser().getId()) {
        		user.setUsed(true);
        	}
        });
        
        model.addAttribute("tags", tags);
        model.addAttribute("users", users);

        model.addAttribute("article", articleService.findByIdWithTags(id));
        return "article/add";
    }

    @PostMapping("/save")
    public String adminsaveArticle(@Valid @ModelAttribute("article") Article article, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){

            model.addAttribute("tags", tagService.getAllTags());
            model.addAttribute("article",article);
            model.addAttribute("users", userService.getAllUsers());
            return "article/add";
        }
        articleService.save(article);
        return "redirect:/article/";
    }

    @GetMapping("/delete/{page}/{id}")
    public String admindelete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        articleService.deleteById(id);
        return "redirect:/article/page/"+page;
    }
}

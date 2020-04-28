package com.isi.controllers;


import com.isi.entities.Tag;
import com.isi.exceptions.ResourceNotFoundException;
import com.isi.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping(value = {"/","/page/{id}"})
    public String home(@PathVariable(name="id",required = false) Optional<Integer> id, ModelMap model)
    {
            Page<Tag> pages = tagService.getAllTags(id, 1, "id");
            model.addAttribute("pageable", pages);
        return "tags/home";
    }


    @GetMapping("/add")
    public String adminadd(ModelMap model,Tag tag, HttpSession session) {
    		System.out.println(session.getAttribute("user"));
            model.addAttribute("tag", tag);
       return "tags/add";
    }

    @GetMapping("/add/{id}")
    public String edit(@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        model.addAttribute("tag", tagService.findById(id));
        return "tags/add";
    }

    @PostMapping("/save")
    public String adminsaveTag(@Valid @ModelAttribute("tag") Tag tag, BindingResult result, ModelMap model) throws ResourceNotFoundException {
        if(result.hasErrors()){
            model.addAttribute("tag",tag);
            return "tags/add";
        }
        tagService.save(tag);
        return "redirect:/tag/";
    }

    @GetMapping("/delete/{page}/{id}")
    public String admindelete(@PathVariable("page") long page,@PathVariable("id") long id, ModelMap model) throws ResourceNotFoundException {
        tagService.deleteById(id);
        return "redirect:/tag/page/"+page;
    }






}

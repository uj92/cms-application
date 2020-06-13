package com.example.sepm.sepm.controllers;

import java.security.Principal;
import java.util.List;

import com.example.sepm.sepm.Model.AdminRepository;
import com.example.sepm.sepm.Model.DiaryRepository;
import com.example.sepm.sepm.Model.PageRepository;
import com.example.sepm.sepm.Model.UserRepository;
import com.example.sepm.sepm.Model.data.Admin;
import com.example.sepm.sepm.Model.data.Diary;
import com.example.sepm.sepm.Model.data.Page;
import com.example.sepm.sepm.Model.data.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PagesController {

    @Autowired
    private PageRepository pageRepo;

    @Autowired
    private DiaryRepository diaryRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired 
    private AdminRepository adminRepo;

    @GetMapping
    public String home(Model model, Principal principal) {
        
        String type = "user";
        if(principal != null){
            User user = userRepo.findByUsername(principal.getName());
            Admin admin = adminRepo.findByUsername(principal.getName());
            if (user != null) {
                type="user";
                List<Diary> diary1 = diaryRepo.findByAvailable(1);
                model.addAttribute("diary", diary1);        
                Page page = pageRepo.findBySlug("home");
                model.addAttribute("page", page);
                model.addAttribute("userType", type);
                return "products";
            }
            if (admin != null){
                type="admin";
                return "adminPage";
            }
        }
        List<Diary> diary1 = diaryRepo.findByAvailable(1);
        model.addAttribute("diary", diary1);        
        Page page = pageRepo.findBySlug("home");
        model.addAttribute("page", page);
        return "products";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/{slug}")
    public String page(@PathVariable String slug, Model model) {
        
        Page page = pageRepo.findBySlug(slug);

        if (page == null) {
            return "redirect:/";
        }
        
        model.addAttribute("page", page);
        
        return "page";
    }    
}
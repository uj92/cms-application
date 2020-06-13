package com.example.sepm.sepm.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.example.sepm.sepm.Model.AdminRepository;
import com.example.sepm.sepm.Model.data.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/admin")
public class AdminAdminController {

    @Autowired
    private AdminRepository adminRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index(Model model){

        List<Admin> admin = adminRepo.findAll();
        model.addAttribute("admin", admin);

        return "admin/admin/index";
    }


    @GetMapping("/add")
    public String add(Admin admin, Model model){

        model.addAttribute("admin", admin);

        return "/admin/admin/add";
    }



    @PostMapping("/add")
    public String add(@Valid Admin admin, 
                        BindingResult bindingResult, 
                        RedirectAttributes redirectAttributes, 
                        Model model) throws IOException {

        List<Admin> admin1 = adminRepo.findAll();

        if (bindingResult.hasErrors()) {
            model.addAttribute("admin", admin1);
            return "admin/admin/add";
        }
        

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        redirectAttributes.addFlashAttribute("message", "Product added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        adminRepo.save(admin);
        return "redirect:/admin/admin/add";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
        
        adminRepo.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Product deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/admin";
    }

}
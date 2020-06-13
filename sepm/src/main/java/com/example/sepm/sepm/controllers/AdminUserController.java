package com.example.sepm.sepm.controllers;

import java.io.IOException;
import java.util.List;

import com.example.sepm.sepm.Model.UserRepository;
import com.example.sepm.sepm.Model.data.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping
    public String index(Model model){

        List<User> user = userRepo.findAll();
        model.addAttribute("user", user);

        return "admin/user/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
        
        userRepo.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Product deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/user";
    }
}
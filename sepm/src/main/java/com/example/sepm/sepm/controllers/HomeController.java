package com.example.sepm.sepm.controllers;

import java.util.List;

import com.example.sepm.sepm.Model.DiaryRepository;
import com.example.sepm.sepm.Model.data.Diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diary")
public class HomeController {

    @Autowired
    private DiaryRepository diaryRepo;

    @GetMapping
    public String index(Model model){

        List<Diary> diary = diaryRepo.findAll();
        model.addAttribute("diary", diary);

        return "products";
    }
}
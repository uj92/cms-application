package com.example.sepm.sepm.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.example.sepm.sepm.Model.DiaryRepository;
import com.example.sepm.sepm.Model.data.Diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin/diary")
public class AdminDiaryController{

    @Autowired
    private DiaryRepository diaryRepo;

    @GetMapping
    public String index(Model model){

        List<Diary> diary = diaryRepo.findAll();
        model.addAttribute("diary", diary);

        return "admin/diary/index";
    }


    @GetMapping("/add")
    public String add(Diary diary, Model model){
        List<Diary> diari = diaryRepo.findAll();
        model.addAttribute("diari", diari);

        return "admin/diary/add";
    }


    @PostMapping("/add")
    public String add(@Valid Diary diary, 
                        BindingResult bindingResult, 
                        RedirectAttributes redirectAttributes, 
                        Model model) throws IOException {

        List<Diary> diari = diaryRepo.findAll();

        if (bindingResult.hasErrors()) {
            model.addAttribute("diari", diari);
            return "admin/diary/add";
        }

        redirectAttributes.addFlashAttribute("message", "Product added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        diaryRepo.save(diary);
        return "redirect:/admin/diary/add";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {

        Diary diary = diaryRepo.getOne(id);
        model.addAttribute("diary", diary);
        return "admin/diary/edit";
    }


    @PostMapping("/edit")
    public String edit(@Valid Diary diary, 
                        BindingResult bindingResult, 
                        RedirectAttributes redirectAttributes, 
                        Model model) throws IOException {

        Diary currentDiary = diaryRepo.getOne(diary.getId());

        if (bindingResult.hasErrors()) {
            model.addAttribute("diaryid", currentDiary.getId());
            return "admin/diary/add";
        }

        redirectAttributes.addFlashAttribute("message", "Diary edited");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        diaryRepo.save(diary);
        return "redirect:/admin/diary";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
        
        diaryRepo.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Product deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/diary";
    }
    
}
package com.example.sepm.sepm.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import com.example.sepm.sepm.Model.DiaryRepository;
import com.example.sepm.sepm.Model.OrderRepository;
import com.example.sepm.sepm.Model.UserRepository;
import com.example.sepm.sepm.Model.feedbackRepository;
import com.example.sepm.sepm.Model.data.Diary;
import com.example.sepm.sepm.Model.data.Feedback;
import com.example.sepm.sepm.Model.data.Order;
import com.example.sepm.sepm.Model.data.User;

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
@RequestMapping("/user")
public class UserOrderController {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DiaryRepository diaryRepo;

    @Autowired
    private feedbackRepository feedbackRepo;

    // @Autowired
    //private Order createOrder;

    @GetMapping
    public String index(Model model){

        List<Order> order = orderRepo.findAll();
        model.addAttribute("order", order);

        return "products";
    }

    @GetMapping("/orders")
    public String orders(Model model, Principal principal){

        if(principal != null){
            
            User user = userRepo.findByUsername(principal.getName());

            if (user == null) {
                return "redirect:/";
            }

            Integer currentUserId = user.getId();
            List<Order> currentUsersOrders1 = orderRepo.findByUserid(currentUserId);
            model.addAttribute("order", currentUsersOrders1);
        }
        else{
            return "redirect:/";
        }

        return "user/userOrder";
    }

    @GetMapping("/createOrder/{id}")
    public String order(@PathVariable int id, Model model, Principal principal, Order order){

        Diary diary = diaryRepo.getOne(id);
        //List<Order> order = orderRepo.findAll();
        if(principal != null){
            
            User user = userRepo.findByUsername(principal.getName());

            if (user == null) {
                return "redirect:/";
            }

            Integer currentUserId = user.getId();
            Integer currentDiaryid = diary.getId();
            Double currentDiaryPrice = diary.getPrice();

            model.addAttribute("currentDiaryid", currentDiaryid);
            model.addAttribute("currentUserid", currentUserId);
            model.addAttribute("diaryPrice", currentDiaryPrice);
            
        }  
        else{
            return "redirect:/";
        }      
        return "user/createOrder";
    }

    @PostMapping("/createOrder")
    public String add(@Valid Order order, 
                        BindingResult bindingResult, 
                        RedirectAttributes redirectAttributes,
                        Principal principal,  
                        Model model) throws IOException {

        if(principal != null){
            
            User user = userRepo.findByUsername(principal.getName());

            if (user == null) {
                return "redirect:/";
            }
            Integer currentUserId = user.getId();
            int currentDiaryid = order.getDiaryid();
            Diary diary = diaryRepo.getOne(currentDiaryid);
            double diaryPrice = diary.getPrice();
            String deliveryType = order.getDelivery();
            if(deliveryType.equals("Express")){
                diaryPrice = diaryPrice +5;
                order.setPrice(diaryPrice);
            }
            else{
                order.setPrice(diaryPrice);
            }
            order.setUserid(currentUserId);
            orderRepo.save(order);
        }
        return "redirect:/products";
    }

    @GetMapping("/addfeedback/{id}")
    public String addfeedback(@PathVariable int id, Model model, Feedback feedback){
       
        Order order = orderRepo.getOne(id);    
        model.addAttribute("order", order); 
        model.addAttribute("orderid", order.getId());           
        return "user/addfeedback";
    }

    @PostMapping("/addfeedback")//
    public String addfeedback(@Valid Feedback feedback, 
                        BindingResult bindingResult, 
                        Principal principal, 
                        RedirectAttributes redirectAttributes, 
                        Model model) throws IOException {

            if(principal != null){
            
                User user = userRepo.findByUsername(principal.getName());
                Order order = orderRepo.getOne(feedback.getOrderid());
                feedback.setDiaryid(order.getDiaryid());
                feedback.setUserid(user.getId());
                // System.out.println(feedback.getFeedbacktext());
                // System.out.println(feedback.getOrderid());
                feedbackRepo.save(feedback);
            }
            return "user/addfeedback";
        }

        @GetMapping("/viewFeedback/{id}")
        public String viewFeedback(@PathVariable int id, Model model){

            List<Feedback> feedbackList = feedbackRepo.findByDiaryid(id);
            model.addAttribute("feedback", feedbackList);

            return "user/viewFeedback";
        }
    
}
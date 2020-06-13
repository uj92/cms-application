package com.example.sepm.sepm.controllers;

import java.io.IOException;
import java.util.List;

import com.example.sepm.sepm.Model.OrderRepository;
import com.example.sepm.sepm.Model.data.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    
    @Autowired
    private OrderRepository orderRepo;

    @GetMapping
    public String index(Model model){

        List<Order> order = orderRepo.findAll();
        model.addAttribute("order", order);

        return "admin/order/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) throws IOException {
        
        orderRepo.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Product deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/order";
    }

    @GetMapping("/report")
    public String report(Model model) {
        List<Order> order1 = orderRepo.findAll();
        double total=0;
        for(Order eachOrder : order1){
            total = total+eachOrder.getPrice();
        }
        String totalp = String.valueOf(total);
        System.out.println(totalp);
        model.addAttribute("order", order1);
        model.addAttribute("totalp", totalp);
        return "admin/order/report";
    }

    @PostMapping("/report")
    public String report(RedirectAttributes redirectAttributes, 
                        @RequestParam(value = "month", required=false) Integer month,
                        Order order, 
                        Model model){
        
        List<Order> orderData = orderRepo.findByMonth(month);
        model.addAttribute("order", orderData);
        // return "redirect:/admin/order/viewReport";
        return "admin/order/viewReport";
    }
}

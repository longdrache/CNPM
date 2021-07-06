package com.hcmute.shop.Controller;

import com.hcmute.shop.DAO.GuestBillDAO;
import com.hcmute.shop.Model.GuestBill;
import com.hcmute.shop.Service.GuestBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class OrderController {
    @Autowired
    GuestBillService guestBillService;

    @GetMapping("/order")
    public String orderPage(){
        return "order";
    }
    @GetMapping("/forget")
    public String s(){
        return "forget";
    }

    @PostMapping("/forder")
    public String getOrder(@RequestParam("code") Long code, @RequestParam("email") String email, Model model){
        try {
            if (guestBillService.isBillTrue(email, code)) {
                GuestBill guestBill = guestBillService.findById(code).get();
                model.addAttribute("bill", guestBill.getBill());
                return "forder";
            }
        }catch ( Exception e) {

            return "forder";
        }
        return "forder";
    }
}

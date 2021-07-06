package com.hcmute.shop.Controller;

import com.hcmute.shop.Model.*;
import com.hcmute.shop.DAO.Cart;
import com.hcmute.shop.Model.enumration.Status;
import com.hcmute.shop.Service.BillService;
import com.hcmute.shop.Service.CartService;
import com.hcmute.shop.Service.GuestBillService;
import com.hcmute.shop.Service.ShoesBuyService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller

public class CheckoutController {
    @Autowired
    BillService billService;
    @Autowired
    ShoesBuyService shoesBuyService;
    @Autowired
    CartService cartService;
    @Autowired
    GuestBillService guestBillService;
    @Autowired
    JavaMailSender emailSender;
    @GetMapping("/checkout")
    public String checkout(){
        return "checkout";
    }
    @PostMapping("/pay")
    public  String pay(@ModelAttribute Bill bill,@RequestParam(value = "email",required = false) String email, @ModelAttribute Cart cart, Model model,HttpServletRequest request){
        bill.setStatus(Status.PROCESSING);
        billService.save(bill);
        if(email==null){
            email="";
        }
        else {
            GuestBill guestBill = new GuestBill(email);
            guestBill.setBill(bill);
            guestBillService.save(guestBill);
            model.addAttribute("gbill",guestBill);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Thanks for checkout");
            message.setText("Hello here is your bill code:\n"+guestBill.getId()+"\n Thank you");
            this.emailSender.send(message);

        }

        CartBuy cartBuy = new CartBuy(cart.getTotalQuantity(),cart.getSubPrice(),cart.getShipPrice(),cart.getTotal());
        cartBuy.setBill(bill);
        cartService.save(cartBuy);
        for(Item i : cart.getItems()) {
            ShoesBuy shoesBuy = new ShoesBuy(i.getShoes(),i.getQuantity(),i.getSize(),i.getPrice());
            shoesBuy.setCartBuy(cartBuy);
            shoesBuyService.save(shoesBuy);
        }
        request.getSession().setAttribute("cart", null);

    return "/thank" ;
    }
    @GetMapping("/thank")
    public  String thanks(){

        return "/thank" ;
    }


}

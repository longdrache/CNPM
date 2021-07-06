package com.hcmute.shop.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hcmute.shop.DAO.Cart;
import com.hcmute.shop.Service.SchoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class SessionController {
    @Autowired
    SchoesService schoesService;

    @ModelAttribute
    public void home(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }
        model.addAttribute("cart",cart);

    }

    public  List<Integer> Sort(List<Integer> i){
    i.stream().sorted();
    return i;
}



    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

}

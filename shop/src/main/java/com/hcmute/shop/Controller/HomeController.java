package com.hcmute.shop.Controller;


import com.hcmute.shop.Service.SchoesService;
import com.hcmute.shop.Service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.time.LocalDate;

@Controller
@ControllerAdvice
public class HomeController {
    @Autowired
    SchoesService schoesService;
    @Autowired
    UserService userService;
    @GetMapping("/")
    public  String HomePage(@ModelAttribute(name = "search")  String name, Model model){
        if(name==null) {
            model.addAttribute("list_shoes", schoesService.findAll());
        }
        else{
            if(name.equals("men")||name.equals("women")){
                model.addAttribute("list_shoes", schoesService.findByType(name));
            }
            else {
                model.addAttribute("list_shoes", schoesService.findByName(name));

            }
        }
      return "index";
    }


    @ModelAttribute
    public void Date(Model model){
        LocalDate now = LocalDate.now();
        int date = now.getYear();
        model.addAttribute("date",date);
    }
    @ModelAttribute
    public void User(Model model,Authentication authentication)
    {
    if(authentication!=null&&authentication.isAuthenticated())
        model.addAttribute("user", userService.findByEmail(authentication.getName()));
    }


}

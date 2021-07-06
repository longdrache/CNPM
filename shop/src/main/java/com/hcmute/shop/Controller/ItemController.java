package com.hcmute.shop.Controller;

import com.hcmute.shop.Service.ImageService;
import com.hcmute.shop.Service.SchoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class ItemController {
     @Autowired
     SchoesService schoesService;
     @Autowired
     ImageService imageService;
     @GetMapping("/info/{id}")
     public String item(@PathVariable("id") UUID id, Model model){
          model.addAttribute("info",schoesService.findSchoesById(id).get());
          model.addAttribute("listdemo",imageService.findAll());
          return "item";
     }
}

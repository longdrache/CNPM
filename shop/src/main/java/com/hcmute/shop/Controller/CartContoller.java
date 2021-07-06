package com.hcmute.shop.Controller;

import com.google.gson.Gson;
import com.hcmute.shop.DAO.Cart;
import com.hcmute.shop.Model.Item;
import com.hcmute.shop.Model.Shoes;
import com.hcmute.shop.Service.SchoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class CartContoller {
    @Autowired
    SchoesService schoesService;
    @GetMapping("/cart")
    public  String cartpage(@RequestParam(value = "id",required = false) UUID id,@RequestParam(value = "action",required = false) String action,HttpServletRequest request){
        @SuppressWarnings("unchecked")
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        if(action==null)
        {
            action="";
        }
        if(action.equals("delete")){
            cart.removeItem(id);
        }
        request.getSession().setAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/cart")
    public String cart(@RequestParam("id") UUID id, @RequestParam(value = "quantity",required = false) int quantity, @RequestParam(value = "size",required = false)  int size, HttpServletRequest request, Model model) {
        @SuppressWarnings("unchecked")
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }




            Shoes shoes = schoesService.findSchoesById(id).get();
            Item item = new Item(shoes);
            item.setQuantity(quantity);
            item.setSize(size);
            boolean present = cart.getItems().stream().parallel().filter(e -> e.getShoes().getId().equals(id)).findFirst().isPresent();
            if (present) {
                cart.editItem(item);
            } else {
                cart.addItem(item);
            }

        request.getSession().setAttribute("cart", cart);
        return "cart";
    }
    @ResponseBody
    @PostMapping("/cartinfo")
    public String cartInfo(HttpServletRequest request){
        Cart cart= (Cart) request.getSession().getAttribute("cart");
        Gson gson = new Gson();
        return gson.toJson(cart);
    }



}

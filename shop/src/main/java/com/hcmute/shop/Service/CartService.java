package com.hcmute.shop.Service;

import com.hcmute.shop.DAO.CartDAO;
import com.hcmute.shop.Model.CartBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartDAO cartDAO;
    public  void save(CartBuy cartBuy){
        cartDAO.save(cartBuy);
    }
}

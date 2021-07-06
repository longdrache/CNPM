package com.hcmute.shop.Service;

import com.hcmute.shop.DAO.ShoesBuyDAO;
import com.hcmute.shop.Model.ShoesBuy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoesBuyService {
    @Autowired
    ShoesBuyDAO shoesBuyDAO;
    public  void save(ShoesBuy shoesBuy){
        shoesBuyDAO.save(shoesBuy);
    }
}

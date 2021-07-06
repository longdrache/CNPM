package com.hcmute.shop.Service;

import com.hcmute.shop.DAO.GuestBillDAO;
import com.hcmute.shop.Model.GuestBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestBillService {
    @Autowired
    GuestBillDAO guestBillDAO;
    public  void save(GuestBill guestBill){
        guestBillDAO.save(guestBill);
    }
    public  boolean isBillTrue(String email,Long id){
        return guestBillDAO.isBillTue(email,id);
    }
    public Optional<GuestBill> findById(Long id){
        return guestBillDAO.findById(id);
    }
}

package com.hcmute.shop.Service;

import com.hcmute.shop.DAO.BillDAO;
import com.hcmute.shop.Model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {
    @Autowired
    BillDAO billDAO;
    public void save(Bill bill){
        billDAO.save(bill);
    }
}

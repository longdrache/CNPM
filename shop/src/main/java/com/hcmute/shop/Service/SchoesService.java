package com.hcmute.shop.Service;

import com.hcmute.shop.DAO.ShoesDAO;
import com.hcmute.shop.Model.Shoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchoesService {
    @Autowired
    ShoesDAO shoesDAO;
    public List<Shoes> findAll(){
       return shoesDAO.findAll();
    }
    public Optional<Shoes> findSchoesById(UUID id){
        return  shoesDAO.findById(id);
    }
    public List<Shoes> findByName(String name){
        String find = "%"+name+"%";
        return shoesDAO.findByLikeName(find);
    }
    public List<Shoes> findByType(String type){
        return shoesDAO.findByType(type);
    }

}

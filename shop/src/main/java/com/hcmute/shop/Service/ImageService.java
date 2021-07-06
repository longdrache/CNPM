package com.hcmute.shop.Service;

import com.hcmute.shop.DAO.ImageDAO;
import com.hcmute.shop.Model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageDAO imageDAO;
    public List<Image> findAll(){
        return imageDAO.findAll();
    }
}

package com.hcmute.shop.Service;

import com.hcmute.shop.DAO.UserDAO;
import com.hcmute.shop.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public Optional<User> findByEmail(String email){
        return userDAO.findByEmail(email);

    }
    public Optional<User> findById(UUID id){
        return userDAO.findById(id);

    }
    public void changePassword(UUID id, String password){
         userDAO.changePassword(id,password);
    }
    public boolean isActive(String email){
        return userDAO.isActive(email);
    }


}

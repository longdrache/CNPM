package com.hcmute.shop.Configuration;

import com.hcmute.shop.DAO.UserDAO;
import com.hcmute.shop.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ApplicationUserService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDAO.findByEmail(email);
        user.orElseThrow(()->new UsernameNotFoundException(String.format("User %s not found",email)));
        return user.map(ApplicationUser::new).get();

    }
}

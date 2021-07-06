package com.hcmute.shop.DAO;



import com.google.gson.Gson;
import com.hcmute.shop.Model.Shoes;
import com.hcmute.shop.Model.User;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentDAOTest {
    @Autowired
    ShoesDAO shoesDAO;
        @Autowired
    UserDAO userDAO;
    @Test
    public void test() {
        PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
   String b=     passwordEncoder.encode(UUID.randomUUID().toString());


        int a= 0;


    }
}

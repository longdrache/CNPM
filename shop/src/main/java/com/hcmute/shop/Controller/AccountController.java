package com.hcmute.shop.Controller;

import com.hcmute.shop.Model.User;
import com.hcmute.shop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Controller
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @ResponseBody
    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("passold") String passold,
                               @RequestParam("passnew") String passnew,
                               @RequestParam("id") UUID id,
                               HttpServletResponse response) throws IOException {
        Optional<User> user = userService.findById(id);
        boolean kt = passwordEncoder.matches(passold, user.get().getPassword());
        if (kt == false) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "FAIL PASSWORD!!!");
            return "FAIL";
        } else {
            userService.changePassword(id, passwordEncoder.encode(passnew));
            response.sendError(HttpServletResponse.SC_OK, "FAIL PASSWORD!!!");
            return "OK";

        }
    }
        @PostMapping("/changepassf")
        public String changef(@RequestParam("password1") String password1,@RequestParam("password2") String password2,
                @RequestParam("id") UUID id
           ) throws IOException {
        if(password1.equals(password2)) {
            Optional<User> user = userService.findById(id);
            userService.changePassword(id, passwordEncoder.encode(password1));
            return "ok";
        }
        return "fail";

            }

}


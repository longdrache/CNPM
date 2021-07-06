package com.hcmute.shop.Controller;

import com.hcmute.shop.DAO.MailDAO;
import com.hcmute.shop.DAO.UserDAO;
import com.hcmute.shop.Model.Mail;
import com.hcmute.shop.Model.User;
import com.hcmute.shop.Model.UserSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

@Controller
public class SignupController {
     @Autowired
     public JavaMailSender emailSender;
     @Autowired
     private MailDAO emailDAO;
     @Autowired
     PasswordEncoder passwordEncoder;

     private PasswordEncoder encode = new StandardPasswordEncoder(); //sha256
     @Autowired
     private UserDAO userDAO;
 
     @ResponseBody
     @GetMapping("/sendEmail")
     public String sendSimpleEmail(@RequestParam("email") String email) {
 
         // Create a Simple MailMessage.
         SimpleMailMessage message = new SimpleMailMessage();
 
         message.setTo(email);
         message.setSubject("Test Simple Email");
         message.setText("Hello, Im testing Simple Email");
 
         // Send Message!
         this.emailSender.send(message);
 
         return "Email Sent!";
     }
     @ResponseBody
     @PostMapping("/signup")
     public String sendCodeSignup(@ModelAttribute UserSignup userSignup, Model model, HttpServletResponse response,HttpServletRequest request) throws IOException {
        boolean exist = userDAO.existByEmail(userSignup.getEmail());
         if(exist){
             response.sendError(HttpServletResponse.SC_FOUND,"you have once");
            return "have";
         }else {
             User user = new User(userSignup.getEmail(),userSignup.getName(),passwordEncoder.encode(userSignup.getPassword()));
             SimpleMailMessage message = new SimpleMailMessage();
             UUID token =UUID.randomUUID();
             String url=request.getRequestURL()+"/"+encode.encode(user.getEmail())+"/"+token;
             message.setTo(user.getEmail());
             message.setSubject("Verify account");
             message.setText("Hello here is your authentication code:\n"+url+"\n Thank you");
             this.emailSender.send(message);
             Mail mail = new Mail(token.toString(), user);
             user.getMail_tokenList().add(mail);
             userDAO.save(user);
         }
         response.sendError(HttpServletResponse.SC_OK,"OK");
             return "OK";
     }
     @GetMapping("/signup/{emaile}/{code}")
     public String comfirmCOde(@PathVariable("emaile") String user , @PathVariable("code") String code)  {
             Mail mail_token=emailDAO.findByToken(code).get();
         // Định nghĩa 2 mốc thời gian ban đầu
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         long time1= timestamp.getTime();
         long time2 = mail_token.getCreatedAt().getTime();
         long diff = time1 -time2;
         long diffminutes=diff/(60 * 1000) % 60;
             if((encode.matches(mail_token.getUser().getEmail(),user))&&diffminutes<5){
                 userDAO.activeUser(mail_token.getUser().getId());
                 emailDAO.confirmat(timestamp,mail_token.getId());
                 return "ok";
             }
             if(diffminutes>5){

                 return "timeout";
             }


             return "fail";
     }
    @ResponseBody
    @PostMapping("/forget")
    public String forget(@RequestParam("email") String email, HttpServletResponse response, HttpServletRequest request) throws IOException {
        boolean exist = userDAO.existByEmail(email);

        if(!exist){
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"NO find");
            return "have";
        }else {
            User user = userDAO.findByEmail(email).get();
            SimpleMailMessage message = new SimpleMailMessage();
            UUID token =UUID.randomUUID();
            String url=request.getRequestURL()+"/"+encode.encode(user.getEmail()+user.getPassword())+"/"+token;
            message.setTo(user.getEmail());
            message.setSubject("Verify account");
            message.setText("\n Hello here is your authentication code: \n"+url+"\n Thank you");
            this.emailSender.send(message);
            Mail mail = new Mail(token.toString(), user);
           emailDAO.save(mail);
        }
        response.sendError(HttpServletResponse.SC_OK,"OK");
        return "OK";
    }
    @GetMapping("/forget/{codexd}/{code}")
    public String confirmforget(@PathVariable("codexd") String codexd,
                              @PathVariable("code") String code,Model model){
        Mail mail_token=emailDAO.findByToken(code).get();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long time1= timestamp.getTime();
        long time2 = mail_token.getCreatedAt().getTime();
        long diff = time1 -time2;
        long diffminutes=diff/(60 * 1000) % 60;
        String kt =mail_token.getUser().getEmail()+mail_token.getUser().getPassword();
        boolean ktcode =encode.matches(kt,codexd);
        if(ktcode&&diffminutes<5){
            emailDAO.confirmat(timestamp,mail_token.getId());
            model.addAttribute("id",mail_token.getUser().getId());
            return "forget";
        }
        if(diffminutes>5){


            return "timeout";
        }

            return "fail";


    }
    @ResponseBody
    @PostMapping("/verify")
    public String sendverify(@RequestParam("email") String email, Model model, HttpServletResponse response,HttpServletRequest request) throws IOException {
        boolean exist = userDAO.existByEmail(email);
        if(exist){
            if(userDAO.isActive(email)){
                response.sendError(HttpServletResponse.SC_FOUND, "have one");
                return "FOUND";
            }
            else {
                SimpleMailMessage message = new SimpleMailMessage();
                UUID token = UUID.randomUUID();
                String url = request.getRequestURL() + "/" + encode.encode(email) + "/" + token;
                message.setTo(email);
                message.setSubject("Verify account");
                message.setText("Hello here is your authentication code:\n" + url + "\n Thank you");
                this.emailSender.send(message);
                Mail mail = new Mail(token.toString(), userDAO.findByUser(email).get());
                emailDAO.save(mail);
                response.sendError(HttpServletResponse.SC_OK, "OK");
                return "OK";
            }

        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND,"NOT FOUND");
        return "FAIL";
    }
    @GetMapping("/verify/{emailv}/{code}")
    public String comfirmverify(@PathVariable("emailv") String user , @PathVariable("code") String code)  {
        Mail mail_token=emailDAO.findByToken(code).get();
        // Định nghĩa 2 mốc thời gian ban đầu
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long time1= timestamp.getTime();
        long time2 = mail_token.getCreatedAt().getTime();
        long diff = time1 -time2;
        long diffminutes=diff/(60 * 1000) % 60;
        if((encode.matches(mail_token.getUser().getEmail(),user))&&diffminutes<5){
            userDAO.activeUser(mail_token.getUser().getId());
            emailDAO.confirmat(timestamp,mail_token.getId());
            return "ok";
        }
        if(diffminutes>5){

            return "timeout";
        }


        return "fail";
    }


     
}

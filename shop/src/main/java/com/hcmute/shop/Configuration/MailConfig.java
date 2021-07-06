package com.hcmute.shop.Configuration;

import org.hibernate.annotations.CollectionId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;
@Configuration
public class MailConfig{
     @Bean
     public JavaMailSender getJavaMailSender() throws IOException {
         CrunchifyGetPropertyValues properties = new CrunchifyGetPropertyValues();
         JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         mailSender.setHost("smtp.gmail.com");
         mailSender.setPort(587);
 
         mailSender.setUsername(properties.getPropValues("email","config/config.properties"));
         mailSender.setPassword(properties.getPropValues("password","config/config.properties"));
 
         Properties props = mailSender.getJavaMailProperties();
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.debug", "true");
 
         return mailSender;
     }
}
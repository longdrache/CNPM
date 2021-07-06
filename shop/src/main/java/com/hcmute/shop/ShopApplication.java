package com.hcmute.shop;


import com.hcmute.shop.DAO.*;
import com.hcmute.shop.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

import static com.hcmute.shop.Model.Color.*;


@SpringBootApplication
public class ShopApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);

	}
	@Autowired
	ShoesDAO shoesDAO;
	@Autowired
	ImageDAO imageDAO;
	@Autowired
	SizeDAO sizeDAO;




	public void ssave() {
		Shoes shoes = new Shoes("Nike Air Max Pre-Day BeTrue","Nike",3829000);
		Image image = new Image("Nike Air Max Pre-Day BeTrue","image/air-max-pre-day-betrue.jpeg");









	}

}

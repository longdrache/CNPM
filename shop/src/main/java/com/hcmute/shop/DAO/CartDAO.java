package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.CartBuy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDAO extends JpaRepository<CartBuy,Integer> {
}

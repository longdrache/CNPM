package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.Wsize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeDAO extends JpaRepository<Wsize,Integer> {
}

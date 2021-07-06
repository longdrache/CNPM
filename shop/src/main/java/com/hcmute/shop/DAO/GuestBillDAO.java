package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.GuestBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GuestBillDAO extends JpaRepository<GuestBill,Long> {
    @Query("SELECT CASE WHEN b.email=?1 THEN TRUE ELSE FALSE END FROM GuestBill b WHERE b.id=?2")
    boolean isBillTue(String email,Long id);

    @Override
    Optional<GuestBill> findById(Long aLong);
}

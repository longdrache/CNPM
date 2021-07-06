package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillDAO extends JpaRepository<Bill, UUID> {
}

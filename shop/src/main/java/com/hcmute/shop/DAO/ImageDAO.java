package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ImageDAO  extends JpaRepository<Image, UUID> {
}

package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.Shoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ShoesDAO extends JpaRepository<Shoes, UUID> {
    @Query(value = "SELECT * FROM Shoes  WHERE name ILIKE  :name",nativeQuery = true)
    List<Shoes> findByLikeName(@Param("name") String name);
    @Query("SELECT s FROM Shoes s where s.type=?1")
    List<Shoes> findByType(String type);
}

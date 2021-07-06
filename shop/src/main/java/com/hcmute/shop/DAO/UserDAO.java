package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface  UserDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("UPDATE User u  SET u.isActive = true where u.id = ?1")
    void activeUser(UUID id);
//    @Query(value = "SELECT EXISTS(SELECT * FROM user WHERE email =?1 ORDER BY id DESC)",nativeQuery = true)
//    boolean  exisByEmail(String email);
    @Query("SELECT CASE WHEN COUNT(u)>0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email=?1")
    Boolean existByEmail(String email);


    @Query("Select u from User u where email=?1")
    Optional<User> findByUser(String email);
    @Modifying
    @Transactional
    @Query("UPDATE User u  SET u.password = ?2 where u.id = ?1")
    void changePassword(UUID id,String password);
    @Query("SELECT CASE WHEN u.isActive=true THEN TRUE ELSE FALSE END FROM User u WHERE u.email=?1")
    Boolean isActive(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u  SET u.isActive = true where u.email = ?1")
    void activeUserByEmail(String email);

}

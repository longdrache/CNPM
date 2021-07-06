package com.hcmute.shop.DAO;

import com.hcmute.shop.Model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MailDAO extends JpaRepository<Mail,Integer> {
     Optional<Mail> findById(int id);
     @Query("SELECT MAX(t.id) FROM Mail t where t.m_user.email = ?1")
     int findByByEmail(String email);
     @Transactional
     @Modifying
     @Query("UPDATE Mail t  SET t.confirmedAt = ?1 where t.id = ?2")
     void confirmat(Date date, int  id);
     @Query("SELECT m  from Mail m  where m.m_user=?1")
     Optional<Mail> findByIdUser(UUID id);
     @Query("SELECT m  from Mail m  where m.token=?1")
     Optional<Mail> findByToken(String token);
}

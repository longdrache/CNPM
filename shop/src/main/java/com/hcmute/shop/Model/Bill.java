package com.hcmute.shop.Model;

import com.hcmute.shop.Model.enumration.PayMethode;
import com.hcmute.shop.Model.enumration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bill {
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String revname;
    private String address;
    private String phone;
    private  String country;
    @Enumerated(EnumType.STRING)
    private PayMethode payMethode;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(mappedBy = "bill")
    private CartBuy cartBuy;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(mappedBy = "bill")
    private GuestBill guestBill;


    public Bill(String revname, String address, String phone, String country, PayMethode payMethode) {
        this.revname = revname;
        this.address = address;
        this.phone = phone;
        this.country = country;
        this.payMethode = payMethode;
    }
}

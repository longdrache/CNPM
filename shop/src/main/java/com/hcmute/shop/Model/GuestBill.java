package com.hcmute.shop.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class GuestBill {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq", sequenceName="seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Long id;
    private String email;
    @OneToOne
    @JoinColumn(name="bill_id")
    private Bill bill;

    public GuestBill(String email) {
        this.email = email;
    }
}

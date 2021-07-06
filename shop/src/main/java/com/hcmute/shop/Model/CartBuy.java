package com.hcmute.shop.Model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartBuy {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="seq", sequenceName="seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id")
    private Long id;
    @OneToMany(mappedBy = "cartBuy")
    private List<ShoesBuy> shoesBuys = new ArrayList<>();
    private int totalQuantity;
    private  int totalPrice;
    private int shipPrice ;
    private int total;
    @OneToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public CartBuy(int totalQuantity, int totalPrice, int shipPrice, int total) {
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.shipPrice = shipPrice;
        this.total = total;
    }
}

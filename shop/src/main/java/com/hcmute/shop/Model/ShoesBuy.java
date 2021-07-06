package com.hcmute.shop.Model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ShoesBuy {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq", sequenceName="seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "shoes_id")
    private Shoes shoesBuy;
    private int quantity;
    private int size;
    private int price_sell;
    @ManyToOne
    @JoinColumn(name="cart_id")
    CartBuy cartBuy;

    public ShoesBuy(Shoes shoes, int quantity, int size, int price_sell) {
        this.shoesBuy = shoes;
        this.quantity = quantity;
        this.size = size;
        this.price_sell = price_sell;
    }
}

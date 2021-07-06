package com.hcmute.shop.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Wsize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int size;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "shoes_id")
    private Shoes shoes;

    public Wsize(int size, Integer quantity) {
        this.size = size;
        this.quantity = quantity;
    }


}

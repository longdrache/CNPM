package com.hcmute.shop.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Shoes {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @Column(unique = true,length = 70)
    private String name;
    private String mark;
    private Integer price;
    private String type;
    @Column(columnDefinition = "text",name = "text_intro")
    private String information;
    @OneToMany(mappedBy = "shoes")
    private List<Wsize> wsizeList = new ArrayList<>();
    @OneToMany(mappedBy = "shoes")
    private List<Image> imageList = new ArrayList<>();
    @OneToMany(mappedBy = "shoesBuy")
    private  List<ShoesBuy> shoesBuy = new ArrayList<>();


    public Shoes(String name, String mark, Integer price) {
        this.name = name;
        this.mark = mark;
        this.price = price;
    }

    public String getTotalCurrencyFormat() {
        DecimalFormat d = new DecimalFormat("###,###,###' ₫'");
        return d.format(price);
    }

}

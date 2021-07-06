package com.hcmute.shop.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private  String link;
    @ManyToOne
    @JoinColumn(name = "shoes_id")
    private Shoes shoes;

    public Image(String name, String link) {
        this.name = name;
        this.link = link;
    }


}

package com.hcmute.shop.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private UUID id;
    @Column(length = 40,unique = true)
    private String email;
    @Column(length = 50)
    private  String name;
    @Column(length = 72)
    private String password;
    private boolean isActive;
    @Column(length = 20)
    private String role;
    @OneToMany(mappedBy = "m_user",cascade = CascadeType.ALL)
    List<Mail> mail_tokenList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    List<Bill> bills = new ArrayList<>();

    public User(String email) {
        this.email = email;
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.isActive = false;
        this.role = "ROLE_USER";
    }
}

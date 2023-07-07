package com.example.demo.Assignment.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;
    @Column(name = "role")
    private Integer role;

    public Account(String userName, String password, Integer role, String mail) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.mail = mail;
    }

    public Account(String mail, String password, Integer id, Integer role, String userName) {

        this.mail = mail;
        this.password = password;
        this.id = id;
        this.role = role;
        this.userName = userName;
    }
}
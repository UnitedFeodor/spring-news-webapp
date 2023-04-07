package com.htp.springnewswebapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @Column(name = "users_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @OneToOne
    @MapsId
    @JoinColumn(name = "users_id")
    private User user;
}

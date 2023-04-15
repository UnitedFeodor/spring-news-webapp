package com.htp.springnewswebapp.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
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
    @ToString.Exclude
    private User user;
}

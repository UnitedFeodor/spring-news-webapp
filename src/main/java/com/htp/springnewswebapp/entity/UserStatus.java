package com.htp.springnewswebapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_status")
public class UserStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="title")
    private String title;
}

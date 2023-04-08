package com.htp.springnewswebapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "news_status")
public class NewsStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name="status_name")
    private String title;
}

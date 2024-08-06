package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity //  DDL
@Data
public class Book { // Book(Object) --->ORM(Hibernate) : JPA--->   DB : book  table  생성
   @Id // PK
   @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Long id; // 1,2,3,4,

    @Column(length = 50, nullable = false, unique = true)
    private String title; //  COLUMN varchar(255), not null
    private int price;
    private String author; // authorName(DB)
    private int page;
}
/*
Hibernate:
    drop table if exists book
Hibernate:
    create table book (
        page integer not null,
        price integer not null,
        id bigint not null auto_increment,
        title varchar(50) not null,
        author varchar(255),
        primary key (id)
    ) engine=InnoDB
Hibernate:
    alter table book
       add constraint UK_g0286ag1dlt4473st1ugemd0m unique (title)
 */
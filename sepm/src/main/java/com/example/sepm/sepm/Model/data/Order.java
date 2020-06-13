package com.example.sepm.sepm.Model.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int diaryid;

    @CreationTimestamp
    private LocalDateTime date;

    private double price;

    private int userid;

    private String customtext;

    private String delivery;

    private String address;

    private String state;
    
    private String country;

    private String cardno;

    private String expirymonth;

    private String expiryyear;

    private String cvv;
}
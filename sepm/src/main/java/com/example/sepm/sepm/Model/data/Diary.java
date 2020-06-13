package com.example.sepm.sepm.Model.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="diary")
@Data
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String papercolor;

    private String covertheme;
    
    private String papertype;
    
    private String customtext;
    
    private int available;

    private double price;
}
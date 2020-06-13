package com.example.sepm.sepm.Model.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "pages")
@Data
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=2, message = "Should contain atleast 2 characters")
    private String title;

    private String slug;

    @Size(min=5, message = "Should contain atleast 5 characters")
    private String content;

    private int sorting;
}
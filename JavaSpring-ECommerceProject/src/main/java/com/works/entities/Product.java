package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private int cid;

    @NotBlank
    private String productName;
    @Min(1)
    @Column(nullable = false)
    private double price;
    private String description;
    @PositiveOrZero
    @Column(nullable = false)
    private int quantity;

}

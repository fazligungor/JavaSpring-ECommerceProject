package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
public class JoinProCat {

    @Id
    private int pid;
    private int cid;
    private String productName;
    private String categoryName;
    private double price;
    private String description;
    private int quantity;

}

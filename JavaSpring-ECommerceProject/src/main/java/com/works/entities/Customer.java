package com.works.entities;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import org.hibernate.validator.constraints.Length;


@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(length = 30)
    @Length(min = 3, max = 30, message = "must be minimum 3 characters and max 30 caharacters")
    private String name;

    private String surname;
    @Column(unique = true)
    private String tc;
    @Email
    @Column(unique = true)
    private String email;
    //@Length(message = "must be minimum 6 characters and max 30 caharacters", min = 6, max = 33)
    @NotNull
    private String password;
    private Date birthDate;

}

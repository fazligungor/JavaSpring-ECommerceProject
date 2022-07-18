package com.works.entities;

import lombok.Data;

@Data
public class CustomerPassword {

    private String email;
    private String tc;
    private String oldPassword;
    private String newPassword;

}

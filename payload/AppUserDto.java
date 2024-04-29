package com.usm.payload;

import lombok.Data;

@Data
public class AppUserDto {

    private long id;

    private String name;

    private String userName;

    private String emailId;

    private String password;

    private String userRole;
}

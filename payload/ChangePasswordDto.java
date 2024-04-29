package com.usm.payload;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String userName;

    private String oldPassword;

    private String newPassword;

    private String confPassword;

}

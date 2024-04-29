package com.usm.service;

import com.usm.payload.ChangePasswordDto;
import com.usm.payload.LoginDto;
import com.usm.payload.SignUpUserDto;

public interface SignUpService {
    String SignUpUser(SignUpUserDto signUpUserDto);

    String verifyUser(int otp);

    String loginUser(LoginDto loginDto);

    String ChangePass(ChangePasswordDto changePasswordDto);
}

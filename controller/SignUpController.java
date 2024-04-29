package com.usm.controller;

import com.usm.JSONService.JSONParameter;
import com.usm.payload.ChangePasswordDto;
import com.usm.payload.LoginDto;
import com.usm.payload.SignUpUserDto;
import com.usm.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/SignUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpUserDto signUpUserDto) {
        String string = signUpService.SignUpUser(signUpUserDto);
        return new ResponseEntity<>(string,HttpStatus.OK);
    }

    @PostMapping("/verifyUserOtp")
    public ResponseEntity<String> userVerify(@RequestParam("otp") int otp) {
        String string = signUpService.verifyUser(otp);
        if (string != null) {
            return new ResponseEntity<>(string, HttpStatus.OK);
        }
        return new ResponseEntity<>("Please Enter Correct OTP", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/LoginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        String string = signUpService.loginUser(loginDto);
        JSONParameter jsonParameter=new JSONParameter();
        jsonParameter.setToken(string);
        return new ResponseEntity<>(jsonParameter,HttpStatus.OK);
    }

    @PutMapping("/ChangePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        String string = signUpService.ChangePass(changePasswordDto);
        if (string != null) {
            return new ResponseEntity<>(string, HttpStatus.OK);
        }
        return new ResponseEntity<>("Your ID is not valid", HttpStatus.NOT_FOUND);
    }
}
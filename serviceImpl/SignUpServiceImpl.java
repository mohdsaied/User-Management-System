package com.usm.serviceImpl;

import com.usm.JWT.JWTService;
import com.usm.entity.AppUser;
import com.usm.entity.SignUp;
import com.usm.payload.ChangePasswordDto;
import com.usm.payload.LoginDto;
import com.usm.payload.SignUpUserDto;
import com.usm.repository.AppUserRepository;
import com.usm.repository.SignUpRepository;
import com.usm.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.Random;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SignUpRepository signUpRepository;
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private JWTService jwtService;


    @Override
    public String SignUpUser(@RequestBody SignUpUserDto signUpUserDto) {
        try {
            Random rando = new Random();
            int nextInt = rando.nextInt(9000) + 1000;
            SignUp signUp = new SignUp();
            signUp.setUserName(signUpUserDto.getUserName());
            signUp.setEmailId(signUpUserDto.getEmailId());
            signUp.setOtp(nextInt);
            SignUp save = signUpRepository.save(signUp);
            emailService.sendEmail(signUpUserDto.getEmailId(), "Dear " + signUpUserDto.getUserName(), "This is your One Time Password   " + nextInt);
            return "Email Send SuccessFully on this EmailID...";
        } catch (Exception e) {
            return "This Email is Already Exist";
        }
    }

    @Override
    public String verifyUser(@RequestParam("otp") int otp) {
        Optional<SignUp> byOtp = signUpRepository.findByOtp(otp);
        if (byOtp.isPresent()) {
            SignUp signUp = byOtp.get();
            Boolean verify = signUp.getVerify();
            Integer otp1 = signUp.getOtp();
            if (verify == true) {
                return "User Already Verified....";
            } else if (otp == otp1 && verify == false) {
                signUp.setVerify(true);
                SignUp save = signUpRepository.save(signUp);
                emailService.sendEmail(save.getEmailId(), "Dear " + save.getUserName(), "Verify your Email ----- Welcome to our AirBNB Application. Your Can Book Your House");
                return "Verified successfully ";
            }
        }
        return null;
    }


    @Override
    public String loginUser(@RequestParam LoginDto loginDto) {
        String userName = loginDto.getUserName();
        Optional<AppUser> byUserName = appUserRepository.findByUserName(userName);
        if(byUserName.isPresent()){
            AppUser user = byUserName.get();
            if(BCrypt.checkpw(loginDto.getPassword(),user.getPassword())){
                return jwtService.generateToken(user);
            }
            return "Invalid UserName/Password";

        }
        return "This User is not Exist...";
    }

    @Override
    public String ChangePass(@RequestBody  ChangePasswordDto changePasswordDto) {

        Optional<AppUser> byUserName = appUserRepository.findByUserName(changePasswordDto.getUserName());
        if (byUserName.isPresent()) {
            if (changePasswordDto.getNewPassword().matches(changePasswordDto.getConfPassword())) {
                AppUser user1 = byUserName.get();
                if (user1.getPassword().matches(changePasswordDto.getOldPassword())) {
                    AppUser user = byUserName.get();
                    user.setPassword(changePasswordDto.getNewPassword());
                    AppUser save = appUserRepository.save(user);
                    return save.getPassword();
                }
                return "password is not match";
            }
            return "wrong conform pass";
        }
        return "UserName is not exist";
    }
}

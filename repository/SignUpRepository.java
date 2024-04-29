package com.usm.repository;

import com.usm.entity.SignUp;
import com.usm.payload.LoginDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignUpRepository extends JpaRepository<SignUp, Long> {
    Optional<SignUp> findByOtp(int otp);

    Optional findByEmailId(LoginDto loginDto);
}
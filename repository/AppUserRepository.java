package com.usm.repository;

import com.usm.entity.AppUser;
import com.usm.payload.AppUserDto;
import com.usm.payload.LoginDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
}
package com.usm.controller;

import com.usm.entity.AppUser;
import com.usm.payload.AppUserDto;
import com.usm.repository.AppUserRepository;
import com.usm.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/AppUser")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;
    @Autowired
    private AppUserRepository appUserRepository;



    @PostMapping("/AddUser")
    public ResponseEntity<AppUserDto> addUser(@RequestBody AppUserDto appUserDto){
        AppUserDto dto = appUserService.AddUser(appUserDto);
        System.out.println(appUserDto.getUserName());
        System.out.println(appUserDto.getEmailId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/DeleteUser")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal AppUser appUser){
        Long id = appUser.getId();
        appUserRepository.deleteById(id);
        return new ResponseEntity<>("User Deleted",HttpStatus.OK);
    }
    @GetMapping("/getUserDetail")
    public ResponseEntity<?> getDetail(@AuthenticationPrincipal AppUser appUser){
        Long id = appUser.getId();
        Optional<AppUser> byId = appUserRepository.findById(id);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }

    @GetMapping("/getUser")
    public AppUser user(@AuthenticationPrincipal AppUser appUser){
        return appUser;
    }

    @PutMapping("/updateUser")
    public ResponseEntity<?> UpdateUser(@RequestParam("id")long id, @RequestBody AppUserDto appUserDto) {
        AppUser user = appUserService.UpdateUserDetail(id, appUserDto);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>("This ID is not Exist.....",HttpStatus.NOT_FOUND);
    }
}

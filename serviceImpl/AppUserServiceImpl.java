package com.usm.serviceImpl;

import com.usm.entity.AppUser;
import com.usm.payload.AppUserDto;
import com.usm.repository.AppUserRepository;
import com.usm.service.AppUserService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUserDto AddUser(@RequestBody AppUserDto appUserDto) {
        AppUser appUser = mapToEntity(appUserDto);
        AppUserDto dto = mapTpDtoDto(appUser);
        return dto;
    }


    @Override
    public AppUser mapToEntity(@RequestBody AppUserDto appUserDto){
        AppUser appUser=new AppUser();
        appUser.setName(appUserDto.getName());
        appUser.setUserName(appUserDto.getUserName());
        appUser.setEmailId(appUserDto.getEmailId());
        appUser.setPassword(BCrypt.hashpw(appUserDto.getPassword(),BCrypt.gensalt(10)));
        appUser.setUserRole(appUserDto.getUserRole());
        appUserRepository.save(appUser);
        return appUser;
    }


    @Override
    public AppUserDto mapTpDtoDto(@RequestBody AppUser appUser){
        AppUserDto dto=new AppUserDto();
        dto.setId(appUser.getId());
        dto.setName(appUser.getName());
        dto.setUserName(appUser.getUserName());
        dto.setEmailId(appUser.getEmailId());
        dto.setPassword(appUser.getPassword());
        dto.setUserRole(appUser.getUserRole());
        return dto;
    }

    @Override
    public AppUser UpdateUserDetail(@RequestParam("id") long id, @RequestBody AppUserDto appUserDto) {
        Optional<AppUser> byId = appUserRepository.findById(id);
        if(byId.isPresent()){
            AppUser user = byId.get();
            user.setName(appUserDto.getName());
            user.setUserName(appUserDto.getUserName());
            user.setEmailId(appUserDto.getEmailId());
            AppUser save = appUserRepository.save(user);
            return save;
        }
        return null;
    }


}

package com.usm.service;

import com.usm.entity.AppUser;
import com.usm.payload.AppUserDto;


public interface AppUserService {
    AppUserDto AddUser(AppUserDto appUserDto);


    AppUser mapToEntity(AppUserDto appUserDto);

    AppUserDto mapTpDtoDto(AppUser appUser);


    AppUser UpdateUserDetail(long id, AppUserDto appUserDto);
}

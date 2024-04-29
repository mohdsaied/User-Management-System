package com.usm.JWT;

import com.usm.entity.AppUser;
import com.usm.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTResponseFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(header!=null && header.startsWith("Bearer ")){
            String substring = header.substring(8, header.length() - 1);
            String userName = jwtService.getUserName(substring);

            Optional<AppUser> byUserName = appUserRepository.findByUserName(userName);
            if(byUserName.isPresent()){
                AppUser appUser = byUserName.get();
                System.out.println(appUser);

                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(appUser,null,Collections.singleton(new SimpleGrantedAuthority(appUser.getUserRole())));
                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}

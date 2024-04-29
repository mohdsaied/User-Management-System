package com.usm.security;


import com.usm.JWT.JWTResponseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JWTResponseFilter jwtResponseFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable().cors().disable();
        httpSecurity.addFilterBefore(jwtResponseFilter, AuthorizationFilter.class);
        httpSecurity.authorizeHttpRequests().requestMatchers("/api/AppUser/AddUser","/api/LoginUser","/api/verifyUserOtp","/api/AppUser/updateUser","/api/ChangePassword","/api/SignUp").permitAll()
                .requestMatchers("/api/AppUser/updateUser","/api/ChangePassword","/api/AppUser/DeleteUser","/api/AppUser/getUserDetail","/api/AppUser/getUser","").hasRole("ADMIN")
                .requestMatchers("/api/AppUser/updateUser","/api/country/add").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated();
        return httpSecurity.build();
    }
}

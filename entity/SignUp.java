package com.usm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sign_up")
public class SignUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "email_id", nullable = false, unique = true, length = 128)
    private String emailId;

    @Column(name = "otp", nullable = false)
    private Integer otp;

    @Column(name = "verify", nullable = false)
    private Boolean verify = false;

}
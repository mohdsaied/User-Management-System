package com.usm.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "email_id", nullable = false, unique = true, length = 128)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_role", nullable = false, length = 10)
    private String userRole;

}
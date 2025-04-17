package com.example.movie_app.entity;

import com.example.movie_app.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String username;
    private String displayName;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(nullable = false, unique = true)
    private String email;
    private String phone;
    private String avatar;
    private String password;

    private Boolean isEnabled;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

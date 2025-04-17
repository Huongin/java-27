package com.example.movie_app.model.dto;

import com.example.movie_app.model.enums.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Integer id;
    String username;
    String displayName;
    String email;
    String avatar;
    String phone;
    UserRole role;
}

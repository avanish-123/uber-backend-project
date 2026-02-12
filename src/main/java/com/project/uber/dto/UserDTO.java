package com.project.uber.dto;

import com.project.uber.entities.enums.Role;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String name;
    private String email;
    private Set<Role> roles;
}

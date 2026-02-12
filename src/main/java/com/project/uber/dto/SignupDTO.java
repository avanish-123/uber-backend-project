package com.project.uber.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignupDTO {
    private String name;
    private String email;
    private String password;
}

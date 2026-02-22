package com.project.uber.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RiderDTO {
    private Long id;
    private UserDTO user;
    private Double rating;
}

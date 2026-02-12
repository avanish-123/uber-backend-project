package com.project.uber.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RiderDTO {
    private UserDTO userDTO;
    private Double rating;
}

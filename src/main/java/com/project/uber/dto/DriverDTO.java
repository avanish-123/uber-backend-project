package com.project.uber.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DriverDTO {
    private Long id;
    private UserDTO user;
    private Double rating;
    private Boolean available;
    private String vehicleId;

}

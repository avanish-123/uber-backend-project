package com.project.uber.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RatingDTO {
    private Long rideId;
    private Integer rating;
}

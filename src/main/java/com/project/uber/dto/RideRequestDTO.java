package com.project.uber.dto;

import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideRequestStatus;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RideRequestDTO {
    private Long id;
    private Point pickupPoint;
    private Point dropOffLocation;
    private LocalDateTime requestTime;
    private RiderDTO rider;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}

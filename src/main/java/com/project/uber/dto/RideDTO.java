package com.project.uber.dto;

import com.project.uber.entities.Driver;
import com.project.uber.entities.Rider;
import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideStatus;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RideDTO {
    private Long id;
    private Point pickupLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime;
    private RiderDTO rider;
    private DriverDTO driver;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}

package com.project.uber.entities;

import com.project.uber.entities.enums.PaymentMethod;
import com.project.uber.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;


    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;


    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;


    @Enumerated(EnumType.STRING)
    private RideRequestStatus status;

    private double fare;


    @CreationTimestamp
    private LocalDateTime requestedTime;
}

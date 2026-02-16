package com.project.uber.strategies.impl;

import com.project.uber.entities.RideRequest;
import com.project.uber.services.DistanceService;
import com.project.uber.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService
                .calculateDistance(rideRequest.getPickupLocation(), rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER;
    }
}

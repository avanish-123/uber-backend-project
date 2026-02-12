package com.project.uber.strategies;

import com.project.uber.dto.RideRequestDTO;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDTO rideRequestDTO);
}

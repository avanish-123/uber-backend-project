package com.project.uber.strategies.impl;

import com.project.uber.dto.RideRequestDTO;
import com.project.uber.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDTO rideRequestDTO) {
        return 0;
    }
}

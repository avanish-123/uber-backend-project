package com.project.uber.strategies.impl;

import com.project.uber.dto.RideRequestDTO;
import com.project.uber.entities.Driver;
import com.project.uber.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDTO rideRequestDTO) {
        return List.of();
    }
}

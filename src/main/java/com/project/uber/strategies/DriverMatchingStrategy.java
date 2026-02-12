package com.project.uber.strategies;

import com.project.uber.dto.RideRequestDTO;
import com.project.uber.entities.Driver;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequestDTO rideRequestDTO);
}

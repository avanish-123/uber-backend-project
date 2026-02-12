package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    @Override
    public RideDTO acceptRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDTO endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDTO rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllMRides() {
        return List.of();
    }
}

package com.project.uber.services;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RiderDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DriverService {

    RideDTO acceptRide(Long rideId);

    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideId);

    RideDTO endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating);

    DriverDTO getMyProfile();

    List<RideDTO> getAllMRides();
}

package com.project.uber.services;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface DriverService {

    RideDTO acceptRide(Long rideRequestId);

    RideDTO cancelRide(Long rideId);

    RideDTO startRide(Long rideId, String otp);

    RideDTO endRide(Long rideId);

    RiderDTO rateRider(Long rideId, Integer rating);

    DriverDTO getMyProfile();

    Page<RideDTO> getAllMRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver update(Driver driver);

    Driver updateDriverAvailability(Driver driver, boolean availability);

    Driver createNewDriver(Driver driver);
}

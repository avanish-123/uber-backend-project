package com.project.uber.services;

import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RideRequestDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Rider;
import com.project.uber.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {
    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideId);

    RiderDTO rateDriver(Long rideId, Integer rating);

    RiderDTO getMyProfile();

    Page<RideDTO> getAllMRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}

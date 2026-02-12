package com.project.uber.services.Impl;

import com.project.uber.dto.RideRequestDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Ride;
import com.project.uber.entities.enums.RideStatus;
import com.project.uber.services.RideService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDrider(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Ride createNewRide(RideRequestDTO rideRequestDTO, Driver driver) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideId, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidersOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidersOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }
}

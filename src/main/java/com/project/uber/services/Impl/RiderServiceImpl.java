package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RideRequestDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.RideRequest;
import com.project.uber.entities.Rider;
import com.project.uber.entities.User;
import com.project.uber.entities.enums.RideRequestStatus;
import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.repositories.RideRequestRepository;
import com.project.uber.repositories.RiderRepository;
import com.project.uber.services.RiderService;
import com.project.uber.strategies.DriverMatchingStrategy;
import com.project.uber.strategies.RideFareCalculationStrategy;
import com.project.uber.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;

    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setStatus(RideRequestStatus.PENDING);
        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        rideRequest.setRider(rider);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        rideStrategyManager.driverMatchingStrategy(rideRequest.getRider().getRating()).findMatchingDriver(rideRequest);
        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDTO rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllMRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found with id 1"));
    }
}

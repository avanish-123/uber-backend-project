package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Ride;
import com.project.uber.entities.RideRequest;
import com.project.uber.entities.enums.RideRequestStatus;
import com.project.uber.entities.enums.RideStatus;
import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.repositories.DriverRepository;
import com.project.uber.services.DriverService;
import com.project.uber.services.RideRequestService;
import com.project.uber.services.RideService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    @Override
    @Transactional
    public RideDTO acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if(!rideRequest.getStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride is already accepted by another driver");
        }

        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver can not accept ride due to unavailability");
        }
        currentDriver.setAvailable(false);
        Driver savedDriver = driverRepository.save(currentDriver);
        Ride ride = rideService.createNewRide(rideRequest, savedDriver);
        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!ride.getDriver().equals(driver)){
            throw new RuntimeException("You are not the assigned driver of the ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride can not be canceled");
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driver.setAvailable(true);
        driverRepository.save(driver);
        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver can not start a ride as he has not accepted ir earlier");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride is not confirmed yet hence can not be started, status "+ ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("OTP is not valid, OTP: "+ otp);
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);
        return modelMapper.map(savedRide, RideDTO.class);
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
        Driver driver = driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found"));
        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMRides(PageRequest pageRequest) {
        Driver driver = getCurrentDriver();
        return rideService.getAllRidersOfDriver(driver.getId(), pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found with id 2"));
    }
}

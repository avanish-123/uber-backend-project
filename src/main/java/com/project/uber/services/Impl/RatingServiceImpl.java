package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.Rating;
import com.project.uber.entities.Ride;
import com.project.uber.entities.Rider;
import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.exceptions.RuntimeConflictException;
import com.project.uber.repositories.DriverRepository;
import com.project.uber.repositories.RatingRepository;
import com.project.uber.repositories.RiderRepository;
import com.project.uber.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;
    @Override
    public DriverDTO rateDriver(Ride ride, Integer rating) {
        Rating ratingObject = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found with given ride id: "+ride.getId()));
        Driver driver = ride.getDriver();

        if(ratingObject.getDriverRating() != null){
            throw new RuntimeConflictException("Driver is already rated");
        }

        ratingObject.setDriverRating(rating);


        ratingRepository.save(ratingObject);

        double newCalculatedRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(ra -> ra.getDriverRating())
                .average()
                .orElse(0.0);
        driver.setRating(newCalculatedRating);
        Driver d = driverRepository.save(driver);
        return modelMapper.map(d, DriverDTO.class);
    }

    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {
        Rating ratingObject = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for this ride: "+ride.getId()));
        Rider rider = ride.getRider();

        if(ratingObject.getRiderRating() != null){
            throw new RuntimeConflictException("Rider is already rated");
        }

        ratingObject.setRiderRating(rating);

        ratingRepository.save(ratingObject);

        double newCalculatedRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(ra -> ra.getRiderRating())
                .average()
                .orElse(0.0);

        rider.setRating(newCalculatedRating);
        Rider r = riderRepository.save(rider);
        return modelMapper.map(r, RiderDTO.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating ratingObject = Rating.builder()
                .ride(ride)
                .driver(ride.getDriver())
                .rider(ride.getRider())
                .build();
        ratingRepository.save(ratingObject);
    }
}

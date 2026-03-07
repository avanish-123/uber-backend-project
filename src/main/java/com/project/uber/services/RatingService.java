package com.project.uber.services;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RiderDTO;
import com.project.uber.entities.Ride;

public interface RatingService {
    DriverDTO rateDriver(Ride ride, Integer rating);
    RiderDTO rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);
}

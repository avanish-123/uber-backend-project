package com.project.uber.controllers;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.RideDTO;
import com.project.uber.dto.RideStartDTO;
import com.project.uber.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestId, @RequestBody RideStartDTO rideStartDTO){
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDTO.getOtp()));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDTO> endRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<DriverDTO> getProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }
}

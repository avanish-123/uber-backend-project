package com.project.uber.services.Impl;

import com.project.uber.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceServiceOSRMImpl implements DistanceService {

    @Override
    public double calculateDistance(Point src, Point dest) {
//      TODO:  call third party api to fetch the distance

        return 0;
    }
}

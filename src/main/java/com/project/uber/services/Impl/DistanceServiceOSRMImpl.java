package com.project.uber.services.Impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.uber.services.DistanceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceServiceOSRMImpl implements DistanceService {
    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public double calculateDistance(Point src, Point dest) {
        try{
            String uri = src.getX() + "," + src.getY() + ";" + dest.getX() + "," + dest.getY();
//            OSRMResponseDTO responseDTO = RestClient.builder()
//                    .baseUrl(OSRM_API_BASE_URL)
//                    .build()
//                    .get()
//                    .uri(uri)
//                    .retrieve()
//                    .body(OSRMResponseDTO.class); //
//            System.out.println(responseDTO.getRoutes().get(0).getDistance());
//            return responseDTO.getRoutes().get(0).getDistance()/1000.0;
            OSRMResponseDTO response =
                    restTemplate.getForObject(OSRM_API_BASE_URL+uri, OSRMResponseDTO.class);

            if (response == null ||
                    response.getRoutes() == null ||
                    response.getRoutes().isEmpty()) {

                throw new RuntimeException("Invalid response from OSRM");
            }

            double distance =
                    response.getRoutes().get(0).getDistance();
            return distance/1000;
        } catch (Exception e){
            throw new RuntimeException("Error getting data from OSRM "+ e.getMessage());
        }
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class OSRMResponseDTO {
    private List<OSRMRoute> routes;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class OSRMRoute {
    private Double distance;
}


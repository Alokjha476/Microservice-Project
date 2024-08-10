package com.user.external.service;

import com.user.entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@AllArgsConstructor
@Service
public class CircuitBreakerService {
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory cb;

    public Hotel hotelCall(String hotelId) {
        String hotelUrl = "http://HOTELSERVICE/hotels/" + hotelId;
        return cb.create("hotel-detail").run(() ->
                        restTemplate.getForObject(hotelUrl, Hotel.class)
                , throwable -> new Hotel());
    }
}

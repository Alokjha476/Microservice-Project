package com.user.impl;

import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.entities.User;
import com.user.repositories.UserRepository;
import com.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public User saveUser(User user) {
        // Generate random unique id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();

    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Id not found"));

        // fetch rating of the above user from RATING SERVICE
        //http://localhost:8083/ratings/users/513c3bc1-3357-4f0f-8595-0bc31741b28d
        Rating[] ratingOfUser = restTemplate.getForObject
                ("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        assert ratingOfUser != null;
        logger.info(" {} ", (Object) ratingOfUser);

        //convert Array to List
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        //http://localhost:8082/hotels?hotelId="ae39c0a8-8727-443f-a44e-2a538c2d78f6"
        List<Rating> ratingList = ratings.stream().peek(rating -> { // map call every rating continuously // peek-> use for debugging
            // when the elements streaming and processing
            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity // fetch the hotel with the help of api using rating
                    ("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();
            logger.info("response status code : {}", forEntity.getStatusCode());
            rating.setHotel(hotel);
        }).collect(Collectors.toList());
        // set the hotel rating
        user.setRatings(ratingList);
        return user;
    }
}

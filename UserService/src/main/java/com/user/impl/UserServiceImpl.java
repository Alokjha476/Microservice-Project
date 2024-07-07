package com.user.impl;

import com.user.entities.Rating;
import com.user.entities.User;
import com.user.external.service.HotelService;
import com.user.repositories.UserRepository;
import com.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelService hotelService;
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
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("UserId not found:"));
        ArrayList<Rating> ratingOfUser = restTemplate.getForObject
                ("http://RATINGSERVICE/ratings/users/" + user.getUserId(), ArrayList.class);
        logger.info("{}", ratingOfUser);
        user.setRatings(ratingOfUser);
        return user;
    }

//    @Override
//    public User getUser(String userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Id not found"));
//
//        // fetch rating of the above user from RATING SERVICE
//        Rating[] ratingOfUser = restTemplate.getForObject
//                ("http://RATINGSERVICE/ratings/users/" + user.getUserId(), Rating[].class);
//        logger.info(" {} ", ratingOfUser);
//
////        user.setRatings(ratingOfUser);
//        //convert Array to List
//        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
//        List<Rating> ratingList = ratings.stream().peek(rating -> { // map call every rating continuously // peek-> use for debugging
//            // when the elements streaming and processing
//
////            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity // fetch the hotel with the help of api using rating
////                    ("http://HOTELSERVICE/hotels/" + rating.getHotelId(), Hotel.class);
//            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            //  logger.info("response status code : {}", forEntity.getStatusCode());
//            rating.setHotel(hotel);
//        }).collect(Collectors.toList());
//        // set the hotel rating
//        user.setRatings(ratingList);
//        return user;
//    }

}

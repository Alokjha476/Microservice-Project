package com.user.impl;

import com.user.entities.Rating;
import com.user.entities.User;
import com.user.external.service.HotelService;
import com.user.repositories.UserRepository;
import com.user.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelService hotelService;
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory cb;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(RestTemplate restTemplate, CircuitBreakerFactory cb) {
        this.restTemplate = restTemplate;
        this.cb = cb;
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
        List<Rating> ratingOfUser = cb
                .create("rating")
                .run(() -> restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), ArrayList.class),
                        throwable -> new ArrayList<>()
                );
        logger.info("{}", ratingOfUser);
        user.setRatings(ratingOfUser);
        return user;
    }

//    private List<Rating> defaultRatingList() {
//        Rating rating = new Rating();
//        rating.setRating(5);
//        rating.setRatingId("12345654323456");
//        rating.setUserId("");
//        rating.setFeedback("fuck");
//
//        return Collections.singletonList(rating);
//    }

    @Override
    public void deleteUser() {
        userRepository.deleteAll();
    }

    @Override
    public User updateUser(String userId, User userDetails) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user1 = existingUser.get();
            user1.setName(userDetails.getName());
            user1.setEmail(userDetails.getEmail());
            user1.setAbout(userDetails.getAbout());
            user1.setRatings(userDetails.getRatings());
            userRepository.save(user1);

        }
        return null;
    }


//    @Override
//    public User getUser1(String userId) {
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

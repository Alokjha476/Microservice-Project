package com.user.impl;

import com.user.entities.Hotel;
import com.user.entities.Rating;
import com.user.entities.User;
import com.user.exception.HotelIdNotFoundException;
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

    // find user with ratings of the user
    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("UserId not found:"));
        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/" + user.getUserId(), ArrayList.class);
        logger.info("{}", ratingOfUser);
        user.setRatings(ratingOfUser);
        return user;
    }

    @Override
    public User getUserByHotelId(String hotelId) throws HotelIdNotFoundException {
        User user = userRepository.findById(hotelId).orElseThrow();
        ArrayList<Hotel> hotelsList = restTemplate.getForObject("http://HOTELSERVICE/hotels/users" + user.getUserId(), ArrayList.class);
        logger.info("{}", hotelsList);
        user.setHotels(hotelsList);
        return user;
    }


}

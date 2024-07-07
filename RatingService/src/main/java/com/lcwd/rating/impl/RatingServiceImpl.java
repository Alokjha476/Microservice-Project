package com.lcwd.rating.impl;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.repository.RatingRepo;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;

    @Override
    public Rating craeteRating(Rating rating) {
        String randomId = UUID.randomUUID().toString();
        rating.setRatingId(randomId);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> ratingList() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}

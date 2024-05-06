package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    // create
    Rating craeteRating(Rating rating);



    // get All
    List<Rating> ratingList();


    List<Rating> findByUserId(String userId);
    List<Rating> findByHotelId(String hotelId);

}

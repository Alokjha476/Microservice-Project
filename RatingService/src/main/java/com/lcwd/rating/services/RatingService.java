package com.lcwd.rating.services;

import com.lcwd.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    // create
    Rating craeteRating(Rating rating);



    // get All ratings
    List<Rating> ratingList();


    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);

}

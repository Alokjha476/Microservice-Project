package com.lcwd.rating.controllers;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    // create
    public ResponseEntity<Object> addRating(@RequestBody Rating rating) {
        Rating rating1 = ratingService.craeteRating(rating);
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);

    }

    //get by user id
    @GetMapping("/users/{userId}")
    public ResponseEntity<Object> getRatingByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));

    }

    // get by hotel id
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<Object> getRatingByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));

    }

    // get all
    @GetMapping
    public ResponseEntity<Object> getAllRating() {
        List<Rating> ratings = ratingService.ratingList();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

}

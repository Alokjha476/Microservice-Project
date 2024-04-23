package com.lcwd.hotel.controller;

import com.lcwd.hotel.HotelRepository;
import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // add hotels
    @PostMapping
    public ResponseEntity<Object> addHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = hotelService.craeteHotel(hotel);
        return new ResponseEntity<>(hotel1, HttpStatus.OK);
    }

    // get one
    @GetMapping("/{hotelId}")
    public ResponseEntity<Object> getOne(String hotelId) {
        Hotel hotel = hotelService.getOne(hotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);

    }

    // get all
    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<Hotel> hotels = hotelService.getAll();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }
}

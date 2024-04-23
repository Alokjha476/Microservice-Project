package com.lcwd.hotel.impl;

import com.lcwd.hotel.HotelRepository;
import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel craeteHotel(Hotel hotel) {
      String randomId =   UUID.randomUUID().toString();
      hotel.setHotelId(randomId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
     return hotelRepository.findAll();
    }

    @Override
    public Hotel getOne(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new RuntimeException("Id not found"));
    }
}

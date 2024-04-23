package com.lcwd.hotel.services;

import com.lcwd.hotel.HotelServiceApplication;
import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel craeteHotel(Hotel hotel);

    List<Hotel> getAll();

    Hotel getOne(String hotelId);
}

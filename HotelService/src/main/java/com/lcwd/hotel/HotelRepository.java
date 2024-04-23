package com.lcwd.hotel;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HotelRepository extends JpaRepository<Hotel, String> {
}

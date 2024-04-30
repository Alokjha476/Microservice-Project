package com.user.entities;


import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
    private String hotelId;
    private String name;
    private String location;
    private String about;
    @Transient
    private List<Rating> ratings;
}

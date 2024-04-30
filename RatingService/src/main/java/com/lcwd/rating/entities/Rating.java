package com.lcwd.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    private String ratingId;
    private String userId;
    private String hotelId;
    private Integer rating;
    private String feedback;
}



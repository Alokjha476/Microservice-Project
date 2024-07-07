package com.lcwd.rating.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @NotNull
    private String ratingId;
    @NotNull
    private String userId;
    @NotNull
    private String hotelId;
    @NotNull
    private Integer rating;
    @Max(value = 50, message = "Kindly enter your feedback")
  //  @Size(min = 5, max= 10)
    private String feedback;
}



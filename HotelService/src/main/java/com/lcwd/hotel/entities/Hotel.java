package com.lcwd.hotel.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Data  // getter, setter, default cons,  --> lombok
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hotels")
public class Hotel {
    @Id
    private String hotelId;

    private String name;

    private String location;
    private String about;


}

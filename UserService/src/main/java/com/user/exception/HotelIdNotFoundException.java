package com.user.exception;

import com.user.entities.Hotel;

public class HotelIdNotFoundException extends RuntimeException{

   public HotelIdNotFoundException(){
        super("Hotel id not found :");

    }
    public HotelIdNotFoundException(String msg){
       super(msg);

    }

}

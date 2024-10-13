package com.user.controller;

import com.user.entities.User;
import com.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService userService;

    // Add the user
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    // get one user
    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable String userId) {
        User user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Object> getUsersByHotelId(@PathVariable String hotelId) {
        User user = userService.getUserByHotelId(hotelId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    // get all user
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllUser() {
        List<User> userList = userService.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


}

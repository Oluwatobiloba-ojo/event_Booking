package org.semicolon.event_Booking.controller;


import org.semicolon.event_Booking.dtos.request.CreateAccountRequest;
import org.semicolon.event_Booking.dtos.response.ApiResponse;
import org.semicolon.event_Booking.dtos.response.CreateAccountResponse;
import org.semicolon.event_Booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request){
        try{
           return ResponseEntity.ok().body(new ApiResponse(userService.createAccount(request), true));
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(new ApiResponse(exception.getMessage(), false));
        }
    }
}

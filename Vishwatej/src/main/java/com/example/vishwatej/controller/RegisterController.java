package com.example.vishwatej.controller;

import com.example.vishwatej.model.User;
import com.example.vishwatej.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {

    @Autowired
    UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<String> registerTheUser(@RequestBody User user){

        String str = userAuthService.registerUser(user);
        return ResponseEntity.ok().body(str);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginTheUser(@RequestBody User user){

        String str = userAuthService.login(user);

        if(str.equals("User logged in successful")){
            return ResponseEntity.ok().body(str);
        }else {
            return ResponseEntity.badRequest().body(str);
        }
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUsers(){

       List<User> userList =   userAuthService.getAllUser();
        return ResponseEntity.ok().body(userList);
    }

    @PutMapping("/city")
    public ResponseEntity<String> updateCityName(@RequestParam String cityName,@RequestParam String username){

       String str =  userAuthService.changeCityName(cityName,username);
        return ResponseEntity.ok().body(str);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTheUser(@RequestParam String username){

       String str = userAuthService.deleteUser(username);

        if(str.equals("User deleted Successfully")){
            return ResponseEntity.ok().body(str);
        }else {
            return ResponseEntity.badRequest().body(str);
        }

    }
}

package com.example.vishwatej.service;


import com.example.vishwatej.model.User;
import com.example.vishwatej.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAuthService {

    @Autowired
    UserRepository userRepository;

    public String registerUser(User user){

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser.isPresent()){
            return "User already exist";
        }
        userRepository.save(user);
        return "User registered successful";
    }

    public String login(User user){

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if(existingUser.isPresent()){

            User user1 = existingUser.get();

            if(user1.getPassword().equals(user.getPassword())){
                return "User logged in successful";
            }

        }
        return "Username or password is incorrect";
    }

    public List<User> getAllUser(){

        List<User> userList = userRepository.findAll();
        return userList;
    }

    public String changeCityName(String cityName,String username){

        Optional<User> existingUser = userRepository.findByUsername(username);
        if(existingUser.isPresent()){

            User user = existingUser.get();

            user.setCity(cityName);

            userRepository.save(user);

            return "City updated Successfully";
        }
        return "User does not exist";
    }

    public String deleteUser(String username) {

        Optional<User> existingUser = userRepository.findByUsername(username);

        if(existingUser.isPresent()){

            User user = existingUser.get();

            userRepository.delete(user);
            return "User deleted Successfully";
        }
        return "User does not Exist";
    }
}

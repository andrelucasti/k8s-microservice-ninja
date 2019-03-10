package com.k8s.learning.learningk8s.controller;


import com.k8s.learning.learningk8s.service.UserBIServices;
import com.k8s.learning.learningk8s.model.User;
import com.k8s.learning.learningk8s.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping(value = "users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBIServices userBIServices;

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> save(@RequestBody User user){

        try {

            user.setAge(userBIServices.calcAgeUser(user.getBirthDate()));
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error save User", e);
        }


    }
}

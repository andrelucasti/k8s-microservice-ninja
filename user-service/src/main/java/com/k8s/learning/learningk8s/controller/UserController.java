package com.k8s.learning.learningk8s.controller;


import com.k8s.learning.learningk8s.service.UserBIServices;
import com.k8s.learning.learningk8s.model.User;
import com.k8s.learning.learningk8s.repository.UserRepository;
import com.k8s.learning.learningk8s.service.UserCalcAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RequestMapping(value = "users")
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserBIServices userBIServices;
    @Autowired
    private UserCalcAgeService userCalcAgeService;

    @GetMapping
    @RequestMapping("/ping")
    public String pingHost(){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            return "IP ADDRESS: - "+inetAddress.getHostAddress() + "\n HOSTNAME: - " + inetAddress.getHostName();
        } catch (UnknownHostException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "ERROR PING SERVER", e);
        }


    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<User> save(@RequestBody User user){

        try {

            //user.setAge(userBIServices.calcAgeUser(user.getBirthDate()));
            user.setAge(userCalcAgeService.getAgeUser(user.getBirthDate()));
            userRepository.save(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error save User", e);
        }


    }
}

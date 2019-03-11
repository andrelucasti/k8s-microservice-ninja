package com.k8s.learning.learningk8s.ageservice.controller;

import com.k8s.learning.learningk8s.ageservice.services.AgeCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestController
@RequestMapping("age")
public class AgeController {
    @Autowired
    private AgeCalcService ageCalcService;

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

    @GetMapping("/{birthDate}")
    public ResponseEntity<Integer> getAge(@PathVariable Date birthDate){
        try{
            Integer age = ageCalcService.execute(birthDate);
            return new ResponseEntity<>(age, HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error in calc age service", e);
        }

    }


}

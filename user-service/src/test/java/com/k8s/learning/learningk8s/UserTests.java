package com.k8s.learning.learningk8s;

import com.k8s.learning.learningk8s.controller.UserController;
import com.k8s.learning.learningk8s.model.User;
import com.k8s.learning.learningk8s.repository.UserRepository;
import com.k8s.learning.learningk8s.service.UserBIServices;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTests extends UserApplicationTests {

    @Autowired
    private UserBIServices userBIServices;
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;


    private String URL = "http://localhost:" + super.port + "/users/";

    @Override
    public void contextLoads() {
        assertThat(userBIServices).isNotNull();
        assertThat(userController).isNotNull();
        assertThat(userRepository).isNotNull();
    }


    @Test
    public void calcAgeTest(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1994, 03, 02);


        Integer age = userBIServices.calcAgeUser(calendar.getTime());

        assertThat(age).isEqualTo(25);
    }

}

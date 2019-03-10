package com.k8s.learning.learningk8s.service;


import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserBIServices {

    public Integer calcAgeUser(Date birthDate){


        Calendar calendarDateNow = Calendar.getInstance();
        calendarDateNow.setTime(new Date());
        int yearNow = calendarDateNow.get(Calendar.YEAR);

        Calendar calendarBirthDate = Calendar.getInstance();
        calendarBirthDate.setTime(birthDate);
        int yearBirtDate = calendarBirthDate.get(Calendar.YEAR);

        int age = yearNow - yearBirtDate;


        return age;
    }
}

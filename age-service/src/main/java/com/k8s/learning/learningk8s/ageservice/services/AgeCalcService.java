package com.k8s.learning.learningk8s.ageservice.services;


import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AgeCalcService {

    public Integer execute(Date birthDate){
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

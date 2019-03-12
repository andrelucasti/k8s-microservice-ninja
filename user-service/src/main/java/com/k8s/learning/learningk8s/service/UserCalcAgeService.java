package com.k8s.learning.learningk8s.service;

import com.k8s.learning.learningk8s.feignclient.AgeClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCalcAgeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCalcAgeService.class);

    @Autowired
    private AgeClient ageClient;

    @HystrixCommand(fallbackMethod = "fallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    })
    public Integer getAgeUser(Date birthDate){
        LOGGER.info("Calculating age user");
            try{
                return ageClient.getAge(birthDate);
            }catch (Exception e){
                LOGGER.error("error: ", e);
                throw new RuntimeException(e);
            }

    }

    public Integer fallBack(Date birDate, Throwable hystrixCommand){

        LOGGER.info("Fallback hystrix command");

        return 0;
    }
}

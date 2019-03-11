package com.k8s.learning.learningk8s.service;

import com.k8s.learning.learningk8s.feignclient.AgeClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCalcAgeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCalcAgeService.class);

    private AgeClient ageClient;

    @HystrixCommand(fallbackMethod = "fallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
    })
    public Integer getAgeUser(Date birthDate){
        LOGGER.info("Calculating age user");

        return ageClient.getAge(birthDate);
    }

    public Integer fallBack(Date birDate, Throwable hystrixCommand){

        LOGGER.error("Error hystrix command");
        LOGGER.error(hystrixCommand.getMessage());

        return 0;
    }
}

package com.k8s.learning.learningk8s.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@FeignClient(name = "age-service")
public interface AgeClient {
    @GetMapping("/age/{birthDate}")
    Integer getAge(@PathVariable("birthDate")Date birthDate);
}

package com.k8s.learning.learningk8s.repository;

import com.k8s.learning.learningk8s.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}

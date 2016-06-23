package com.raysmond.service;

import com.raysmond.domain.User;
import com.raysmond.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

/**
 * @author Raysmond<i@raysmond.com>
 */
@Service
public class UserService {
  private List<User> users = new ArrayList<>();

  @Autowired
  private UserRepository userRepository;

  @PostConstruct
  private void init() {
    users.add(new User(1L, "glp sb"));
    users.add(new User(2L, "xiaobai sb"));
    users.add(new User(3L, "sxb sb"));

    users.forEach(user -> userRepository.save(user));
  }



}

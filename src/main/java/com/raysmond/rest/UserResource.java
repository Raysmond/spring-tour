package com.raysmond.rest;

import com.raysmond.domain.User;
import com.raysmond.repository.UserRepository;
import com.raysmond.service.UserService;

import java.util.List;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Raysmond<i@raysmond.com>
 */
@RestController
@RequestMapping("/api/users")
public class UserResource {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  // /api/users
  @RequestMapping(value = "",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // /api/users/
  @RequestMapping(value = "/{name}",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public Page<User> getAllUsers(@PathVariable String name, Pageable pageable) {
    return userRepository.findByNameLike(name, pageable);
  }

  /**
   * GET /api/users/1
   */
  @RequestMapping(value = "/{id:\\d+}",
          method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> get(@PathVariable Long id) {
    User user = userRepository.findOne(id);

    if (user != null) {
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }

  /**
   * 增加一条
   *
   * POST /api/users
   */
  @RequestMapping(value = "",
          method = RequestMethod.POST,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public User create(@RequestBody User params) {
    return userRepository.save(params);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<User> update(@PathVariable Long id,
                                     @Valid @RequestBody User params) {
    if (userRepository.findOne(id) == null){
      return new ResponseEntity<User>(params, HttpStatus.NOT_FOUND);
    }

    User saved = userRepository.save(params);
    return new ResponseEntity<User>(saved, HttpStatus.OK);
  }

}

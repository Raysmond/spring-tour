package com.raysmond.repository;

import com.raysmond.domain.User;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Raysmond<i@raysmond.com>
 */
public interface UserRepository extends JpaRepository<User, Long>{

  Page<User> findByNameLike(String name, Pageable pageable);

  @Query("select user from User user where user.name like ?1")
  Page<User> search(String keyword, Pageable pageable);


}

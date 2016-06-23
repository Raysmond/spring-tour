package com.raysmond.repository;

import com.raysmond.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Raysmond<i@raysmond.com>
 */
public interface UserRepository
        extends JpaRepository<User, Long>{
}

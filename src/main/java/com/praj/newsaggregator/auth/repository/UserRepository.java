package com.praj.newsaggregator.auth.repository;

import com.praj.newsaggregator.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//UserRepository is a Spring Data JPA Repository. By extending JpaRepository, Spring automatically creates an implementation for you at runtime—you don't write any SQL or implementation code.
//Spring Data JPA can derive queries from method names. It parses the method name and generates the appropriate SQL.

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
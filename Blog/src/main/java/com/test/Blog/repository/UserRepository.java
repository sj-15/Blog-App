package com.test.Blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.Blog.models.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);
}

package com.example.lab.backendlab.repository;

import com.example.lab.backendlab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByIdAndPassword(String id, String password);
}

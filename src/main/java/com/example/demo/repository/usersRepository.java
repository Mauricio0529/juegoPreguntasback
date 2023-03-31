package com.example.demo.repository;

import com.example.demo.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usersRepository extends JpaRepository<user, Integer> {
    public user findByUsername(String username);
}
package com.example.demo.repository;

import com.example.demo.models.rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface rolRepository extends JpaRepository<rol, Integer> {

}
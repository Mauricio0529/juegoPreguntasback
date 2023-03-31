package com.example.demo.controller;

import com.example.demo.dto.responseDto.categoryResponseDto;
import com.example.demo.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class categoryController {

    @Autowired
    private categoryService categoryService;

    @GetMapping("/allCategory")
    public ResponseEntity<List<categoryResponseDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
}
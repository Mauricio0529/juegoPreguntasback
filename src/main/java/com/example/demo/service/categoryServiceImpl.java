package com.example.demo.service;

import com.example.demo.dto.categoryDTO;
import com.example.demo.dto.mapperDto.mapperDto;
import com.example.demo.dto.responseDto.categoryResponseDto;
import com.example.demo.models.category;
import com.example.demo.repository.categoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {

    @Autowired
    private categoryRepository categoryRepository;

    @Override
    public categoryResponseDto addCategory(categoryDTO categoryDTO) {
        return null;
    }

    @Override
    public List<categoryResponseDto> getAllCategory() {
        List<category> categoryList = categoryRepository.findAll();
        return mapperDto.categoryResponseDTOList(categoryList);
    }

    @Override
    public List<categoryResponseDto> getAllCategoryStatusTrue(Boolean status) {
        return null;
    }
}
package com.example.demo.service;

import com.example.demo.dto.categoryDTO;
import com.example.demo.dto.responseDto.categoryResponseDto;

import java.util.List;

public interface categoryService {

    public categoryResponseDto addCategory(categoryDTO categoryDTO);
    public List<categoryResponseDto> getAllCategory();
    public List<categoryResponseDto> getAllCategoryStatusTrue(Boolean status);

}
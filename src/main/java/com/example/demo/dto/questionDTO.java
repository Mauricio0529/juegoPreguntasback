package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class questionDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    /* questionRequestDto */

    private String question;
}
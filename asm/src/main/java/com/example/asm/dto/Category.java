package com.example.asm.dto;

import javax.validation.constraints.NotEmpty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String image;
    @NotEmpty
    private String description;
}

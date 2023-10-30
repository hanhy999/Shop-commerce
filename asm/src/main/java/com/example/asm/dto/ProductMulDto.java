package com.example.asm.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMulDto {
    private int id;
    @NotEmpty
    private String name;

    private MultipartFile image;
    // @Min(0)
    // @Max(100)
    @Range(min = 0)
    private double price;

    private Date createDate;

    private boolean available;

    @NotEmpty
    private String description;

    private int category;
}

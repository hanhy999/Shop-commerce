package com.example.asm.dto;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String image;
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

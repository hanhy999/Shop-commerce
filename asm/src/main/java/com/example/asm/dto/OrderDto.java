package com.example.asm.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderDto {
    private int id;
    @NotEmpty(message = "Không được để trông name")
    private String customerName;
    @NotEmpty(message = "Không được để trông name")
    private String note;

    @NotEmpty(message = "Không được để trông name")
    private String address;
    @NotEmpty(message = "Không được để trông name")
    private String phone;
    // @NotEmpty(message = "Không được để trông name")
    private String status;

    // @NotEmpty(message = "Không được để trông name")
    private double total;

    // @NotEmpty(message = "Không được để trông name")
    private String account;

}

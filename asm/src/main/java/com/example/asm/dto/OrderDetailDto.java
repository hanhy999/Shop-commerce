package com.example.asm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private int orderId;
    private int productId;
    private double price;
    private int quantity;
    private double total;
}

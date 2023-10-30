package com.example.asm.service;

import org.springframework.stereotype.Service;

import com.example.asm.domain.Products;

@Service
public interface CartService {
    public int add(Products p);

    public int remove(int id);
}

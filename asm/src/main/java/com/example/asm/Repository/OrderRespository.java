package com.example.asm.Repository;

import com.example.asm.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository  extends JpaRepository <Order, Integer>{
    
}

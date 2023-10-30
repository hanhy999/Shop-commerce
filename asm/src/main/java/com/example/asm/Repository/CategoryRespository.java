package com.example.asm.Repository;


import com.example.asm.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository <Category, Integer> {
    
}

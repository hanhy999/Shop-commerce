package com.example.asm.Repository;

import com.example.asm.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRespository extends JpaRepository<Role, Integer> {
    
}

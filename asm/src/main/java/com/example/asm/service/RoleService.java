package com.example.asm.service;

import com.example.asm.Repository.RoleRespository;
import com.example.asm.domain.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface RoleService {



    void delete(Role entity);

    List<Role> findAll();

    Optional<Role> findById(Integer id);

    Role getById(Integer id);

    <S extends Role> S save(S entity);

   
    
}

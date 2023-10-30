package com.example.asm.serviceImp;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.asm.Repository.RoleRespository;
import com.example.asm.domain.Role;
import com.example.asm.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleRespository roleRespository;

    public List<Role> findAll() {
        return roleRespository.findAll();
    }

    public Role getById(Integer id) {
        return roleRespository.getById(id);
    }

    public <S extends Role> S save(S entity) {
        return roleRespository.save(entity);
    }

    public Optional<Role> findById(Integer id) {
        return roleRespository.findById(id);
    }

    public void delete(Role entity) {
        roleRespository.delete(entity);
    }

}

package com.example.asm.Repository;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.example.asm.domain.OrderDetail;
import com.example.asm.domain.OrderDetailKey;

public interface OrderDetailRes extends JpaRepository<OrderDetail, OrderDetailKey>{

    
}

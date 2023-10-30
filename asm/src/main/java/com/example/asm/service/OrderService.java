package com.example.asm.service;

import com.example.asm.Repository.OrderRespository;
import com.example.asm.domain.Order;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    long count();

    <S extends Order> long count(Example<S> example);

    void delete(Order entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Order> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Order> entities);

    void deleteById(Integer id);

    void deleteInBatch(Iterable<Order> entities);

    boolean equals(Object obj);

    <S extends Order> boolean exists(Example<S> example);

    boolean existsById(Integer id);

    List<Order> findAll();

    <S extends Order> List<S> findAll(Example<S> example);

    <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Order> List<S> findAll(Example<S> example, Sort sort);

    Page<Order> findAll(Pageable pageable);

    List<Order> findAll(Sort sort);

    List<Order> findAllById(Iterable<Integer> ids);

    <S extends Order, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<Order> findById(Integer id);

    <S extends Order> Optional<S> findOne(Example<S> example);

    void flush();

    Order getById(Integer id);

    Order getOne(Integer id);

    int hashCode();

    <S extends Order> S save(S entity);

    <S extends Order> List<S> saveAll(Iterable<S> entities);

    <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Order> S saveAndFlush(S entity);

    String toString();



    
}

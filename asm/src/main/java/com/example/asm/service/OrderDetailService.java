package com.example.asm.service;

import com.example.asm.domain.OrderDetail;
import com.example.asm.domain.OrderDetailKey;
import com.example.asm.dto.OrderDetailDto;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

@Service
public interface OrderDetailService {

    long count();

    <S extends OrderDetail> long count(Example<S> example);

    void delete(OrderDetail entity);

    void deleteAll();

    void deleteAll(Iterable<? extends OrderDetail> entities);

    void deleteAllById(Iterable<? extends OrderDetailKey> ids);

    void deleteAllByIdInBatch(Iterable<OrderDetailKey> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<OrderDetail> entities);

    void deleteById(OrderDetailKey id);

    void deleteInBatch(Iterable<OrderDetail> entities);

    boolean equals(Object obj);

    <S extends OrderDetail> boolean exists(Example<S> example);

    boolean existsById(OrderDetailKey id);

    List<OrderDetail> findAll();

    <S extends OrderDetail> List<S> findAll(Example<S> example);

    <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort);

    Page<OrderDetail> findAll(Pageable pageable);

    List<OrderDetail> findAll(Sort sort);

    List<OrderDetail> findAllById(Iterable<OrderDetailKey> ids);

    // List<OrderDetail> findAllByIdInt(Integer ids);

    <S extends OrderDetail, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<OrderDetail> findById(OrderDetailKey id);

    <S extends OrderDetail> Optional<S> findOne(Example<S> example);

    void flush();

    OrderDetail getById(OrderDetailKey id);

    OrderDetail getOne(OrderDetailKey id);

    int hashCode();

    <S extends OrderDetail> S save(S entity);

    <S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

    <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends OrderDetail> S saveAndFlush(S entity);

    String toString();

    List<OrderDetailDto> getListOrderDetail(int id);
    
}

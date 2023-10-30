package com.example.asm.service;

import com.example.asm.domain.Products;
import com.example.asm.dto.ProductDto;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

public interface ProductService {

    long count();

    <S extends Products> long count(Example<S> example);

    void delete(Products entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Products> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Products> entities);

    void deleteById(Integer id);

    void deleteInBatch(Iterable<Products> entities);

    boolean equals(Object obj);

    <S extends Products> boolean exists(Example<S> example);

    boolean existsById(Integer id);

    List<Products> findAll();

    <S extends Products> List<S> findAll(Example<S> example);

    <S extends Products> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Products> List<S> findAll(Example<S> example, Sort sort);

    Page<Products> findAll(Pageable pageable);

    List<Products> findAll(Sort sort);

    List<Products> findAllById(Iterable<Integer> ids);

    <S extends Products, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<Products> findById(Integer id);

    <S extends Products> Optional<S> findOne(Example<S> example);

    void flush();

    Products getById(Integer id);

    Products getOne(Integer id);

    int hashCode();

    <S extends Products> S save(S entity);

    <S extends Products> List<S> saveAll(Iterable<S> entities);

    <S extends Products> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Products> S saveAndFlush(S entity);

    String toString();

    List<ProductDto> getListCategory(int id);

    List<ProductDto> getListSortAsc();

    List<ProductDto> getListSortDesc();

    List<ProductDto> getListSortName();

    List<ProductDto> search(String name);

}

package com.example.asm.service;

import com.example.asm.domain.Category;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;




public interface CategoryService {

    long count();

    <S extends Category> long count(Example<S> example);

    void delete(Category entity);

    void deleteAll();

    void deleteAll(Iterable<? extends Category> entities);

    void deleteAllById(Iterable<? extends Integer> ids);

    void deleteAllByIdInBatch(Iterable<Integer> ids);

    void deleteAllInBatch();

    void deleteAllInBatch(Iterable<Category> entities);

    void deleteById(Integer id);

    void deleteInBatch(Iterable<Category> entities);

    <S extends Category> boolean exists(Example<S> example);

    boolean existsById(Integer id);

    List<Category> findAll();

    <S extends Category> List<S> findAll(Example<S> example);

    <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Category> List<S> findAll(Example<S> example, Sort sort);

    Page<Category> findAll(Pageable pageable);

    List<Category> findAll(Sort sort);

    List<Category> findAllById(Iterable<Integer> ids);

    <S extends Category, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    Optional<Category> findById(Integer id);

    <S extends Category> Optional<S> findOne(Example<S> example);

    void flush();

    Category getById(Integer id);

    Category getOne(Integer id);

    <S extends Category> S save(S entity);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

    <S extends Category> S saveAndFlush(S entity);

  
    
    
}

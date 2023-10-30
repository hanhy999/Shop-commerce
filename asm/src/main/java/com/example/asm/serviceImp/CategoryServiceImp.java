package com.example.asm.serviceImp;

import com.example.asm.Repository.CategoryRespository;
import com.example.asm.domain.Category;
import com.example.asm.service.CategoryService;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRespository categoryRespository;

    public <S extends Category> S save(S entity) {
        return categoryRespository.save(entity);
    }

    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categoryRespository.findOne(example);
    }

    public List<Category> findAll() {
        return categoryRespository.findAll();
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRespository.findAll(pageable);
    }

    public List<Category> findAll(Sort sort) {
        return categoryRespository.findAll(sort);
    }

    public List<Category> findAllById(Iterable<Integer> ids) {
        return categoryRespository.findAllById(ids);
    }

    public Optional<Category> findById(Integer id) {
        return categoryRespository.findById(id);
    }

    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return categoryRespository.saveAll(entities);
    }

    public void flush() {
        categoryRespository.flush();
    }

    public <S extends Category> S saveAndFlush(S entity) {
        return categoryRespository.saveAndFlush(entity);
    }

    public boolean existsById(Integer id) {
        return categoryRespository.existsById(id);
    }

    public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
        return categoryRespository.saveAllAndFlush(entities);
    }

    public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
        return categoryRespository.findAll(example, pageable);
    }

    public void deleteInBatch(Iterable<Category> entities) {
        categoryRespository.deleteInBatch(entities);
    }

    public <S extends Category> long count(Example<S> example) {
        return categoryRespository.count(example);
    }

    public void deleteAllInBatch(Iterable<Category> entities) {
        categoryRespository.deleteAllInBatch(entities);
    }

    public long count() {
        return categoryRespository.count();
    }

    public <S extends Category> boolean exists(Example<S> example) {
        return categoryRespository.exists(example);
    }

    public void deleteById(Integer id) {
        categoryRespository.deleteById(id);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        categoryRespository.deleteAllByIdInBatch(ids);
    }

    public void delete(Category entity) {
        categoryRespository.delete(entity);
    }

    public <S extends Category, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return categoryRespository.findBy(example, queryFunction);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        categoryRespository.deleteAllById(ids);
    }

    public void deleteAllInBatch() {
        categoryRespository.deleteAllInBatch();
    }

    public Category getOne(Integer id) {
        return categoryRespository.getOne(id);
    }

    public void deleteAll(Iterable<? extends Category> entities) {
        categoryRespository.deleteAll(entities);
    }

    public void deleteAll() {
        categoryRespository.deleteAll();
    }

    public Category getById(Integer id) {
        return categoryRespository.getById(id);
    }

    public <S extends Category> List<S> findAll(Example<S> example) {
        return categoryRespository.findAll(example);
    }

    public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
        return categoryRespository.findAll(example, sort);
    }

  

    
}

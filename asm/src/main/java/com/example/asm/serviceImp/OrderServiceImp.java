package com.example.asm.serviceImp;

import com.example.asm.Repository.OrderRespository;
import com.example.asm.domain.Order;
import com.example.asm.service.OrderService;
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
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRespository orderRespository;

    public List<Order> findAll() {
        return orderRespository.findAll();
    }

    public List<Order> findAll(Sort sort) {
        return orderRespository.findAll(sort);
    }

    public List<Order> findAllById(Iterable<Integer> ids) {
        return orderRespository.findAllById(ids);
    }

    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        return orderRespository.saveAll(entities);
    }

    public void flush() {
        orderRespository.flush();
    }

    public <S extends Order> S saveAndFlush(S entity) {
        return orderRespository.saveAndFlush(entity);
    }

    public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
        return orderRespository.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<Order> entities) {
        orderRespository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Order> entities) {
        orderRespository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        orderRespository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        orderRespository.deleteAllInBatch();
    }

    public Order getOne(Integer id) {
        return orderRespository.getOne(id);
    }

    public Order getById(Integer id) {
        return orderRespository.getById(id);
    }

    public <S extends Order> List<S> findAll(Example<S> example) {
        return orderRespository.findAll(example);
    }

    public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
        return orderRespository.findAll(example, sort);
    }

    public Page<Order> findAll(Pageable pageable) {
        return orderRespository.findAll(pageable);
    }

    public <S extends Order> S save(S entity) {
        return orderRespository.save(entity);
    }

    public Optional<Order> findById(Integer id) {
        return orderRespository.findById(id);
    }

    public boolean existsById(Integer id) {
        return orderRespository.existsById(id);
    }

    public long count() {
        return orderRespository.count();
    }

    public void deleteById(Integer id) {
        orderRespository.deleteById(id);
    }

    public void delete(Order entity) {
        orderRespository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        orderRespository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Order> entities) {
        orderRespository.deleteAll(entities);
    }

    public void deleteAll() {
        orderRespository.deleteAll();
    }

    public <S extends Order> Optional<S> findOne(Example<S> example) {
        return orderRespository.findOne(example);
    }

    public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
        return orderRespository.findAll(example, pageable);
    }

    public <S extends Order> long count(Example<S> example) {
        return orderRespository.count(example);
    }

    public <S extends Order> boolean exists(Example<S> example) {
        return orderRespository.exists(example);
    }

    public <S extends Order, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return orderRespository.findBy(example, queryFunction);
    }

    @Override
    public int hashCode() {
        return orderRespository.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return orderRespository.equals(obj);
    }

    @Override
    public String toString() {
        return orderRespository.toString();
    }

}

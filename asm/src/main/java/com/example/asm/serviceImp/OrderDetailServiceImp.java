package com.example.asm.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.asm.Repository.OrderDetailRes;
import com.example.asm.domain.OrderDetail;
import com.example.asm.domain.OrderDetailKey;
import com.example.asm.dto.OrderDetailDto;
import com.example.asm.service.OrderDetailService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class OrderDetailServiceImp implements OrderDetailService {
    @Autowired
    OrderDetailRes orderDetailRes;

    public List<OrderDetail> findAll() {
        return orderDetailRes.findAll();
    }

    public List<OrderDetail> findAll(Sort sort) {
        return orderDetailRes.findAll(sort);
    }

    public List<OrderDetail> findAllById(Iterable<OrderDetailKey> ids) {
        return orderDetailRes.findAllById(ids);
    }

    public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
        return orderDetailRes.saveAll(entities);
    }

    public void flush() {
        orderDetailRes.flush();
    }

    public <S extends OrderDetail> S saveAndFlush(S entity) {
        return orderDetailRes.saveAndFlush(entity);
    }

    public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
        return orderDetailRes.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<OrderDetail> entities) {
        orderDetailRes.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<OrderDetail> entities) {
        orderDetailRes.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<OrderDetailKey> ids) {
        orderDetailRes.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        orderDetailRes.deleteAllInBatch();
    }

    public OrderDetail getOne(OrderDetailKey id) {
        return orderDetailRes.getOne(id);
    }

    public OrderDetail getById(OrderDetailKey id) {
        return orderDetailRes.getById(id);
    }

    public <S extends OrderDetail> List<S> findAll(Example<S> example) {
        return orderDetailRes.findAll(example);
    }

    public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
        return orderDetailRes.findAll(example, sort);
    }

    public Page<OrderDetail> findAll(Pageable pageable) {
        return orderDetailRes.findAll(pageable);
    }

    public <S extends OrderDetail> S save(S entity) {
        return orderDetailRes.save(entity);
    }

    public Optional<OrderDetail> findById(OrderDetailKey id) {
        return orderDetailRes.findById(id);
    }

    public boolean existsById(OrderDetailKey id) {
        return orderDetailRes.existsById(id);
    }

    public long count() {
        return orderDetailRes.count();
    }

    public void deleteById(OrderDetailKey id) {
        orderDetailRes.deleteById(id);
    }

    public void delete(OrderDetail entity) {
        orderDetailRes.delete(entity);
    }

    public void deleteAllById(Iterable<? extends OrderDetailKey> ids) {
        orderDetailRes.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends OrderDetail> entities) {
        orderDetailRes.deleteAll(entities);
    }

    public void deleteAll() {
        orderDetailRes.deleteAll();
    }

    public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
        return orderDetailRes.findOne(example);
    }

    public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
        return orderDetailRes.findAll(example, pageable);
    }

    public <S extends OrderDetail> long count(Example<S> example) {
        return orderDetailRes.count(example);
    }

    public <S extends OrderDetail> boolean exists(Example<S> example) {
        return orderDetailRes.exists(example);
    }

    public <S extends OrderDetail, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return orderDetailRes.findBy(example, queryFunction);
    }

    @Override
    public int hashCode() {
        return orderDetailRes.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return orderDetailRes.equals(obj);
    }

    @Override
    public String toString() {
        return orderDetailRes.toString();
    }

    public List<OrderDetailDto> getListOrderDetail(int id) {
        Connection conn = DBProvider.getConnection();
        List<OrderDetailDto> ListCat = new ArrayList<OrderDetailDto>();
        try {
            String sql = "SELECT * FROM order_detail where order_id =  ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                OrderDetailDto posts = new OrderDetailDto(rst.getInt(1), rst.getInt(2), rst.getDouble(3), rst.getInt(4),
                        rst.getDouble(5));
                ListCat.add(posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.example.asm.serviceImp;

import com.example.asm.Repository.ProductRepository;
import com.example.asm.domain.Products;
import com.example.asm.dto.ProductDto;
import com.example.asm.service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Products> findAll() {
        return productRepository.findAll();
    }

    public List<Products> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    public List<Products> findAllById(Iterable<Integer> ids) {
        return productRepository.findAllById(ids);
    }

    public <S extends Products> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    public void flush() {
        productRepository.flush();
    }

    public <S extends Products> S saveAndFlush(S entity) {
        return productRepository.saveAndFlush(entity);
    }

    public <S extends Products> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productRepository.saveAllAndFlush(entities);
    }

    public void deleteInBatch(Iterable<Products> entities) {
        productRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Products> entities) {
        productRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        productRepository.deleteAllByIdInBatch(ids);
    }

    public void deleteAllInBatch() {
        productRepository.deleteAllInBatch();
    }

    public Products getOne(Integer id) {
        return productRepository.getOne(id);
    }

    public Products getById(Integer id) {
        return productRepository.getById(id);
    }

    public <S extends Products> List<S> findAll(Example<S> example) {
        return productRepository.findAll(example);
    }

    public <S extends Products> List<S> findAll(Example<S> example, Sort sort) {
        return productRepository.findAll(example, sort);
    }

    public Page<Products> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public <S extends Products> S save(S entity) {
        return productRepository.save(entity);
    }

    public Optional<Products> findById(Integer id) {
        return productRepository.findById(id);
    }

    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }

    public long count() {
        return productRepository.count();
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public void delete(Products entity) {
        productRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> ids) {
        productRepository.deleteAllById(ids);
    }

    public void deleteAll(Iterable<? extends Products> entities) {
        productRepository.deleteAll(entities);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public <S extends Products> Optional<S> findOne(Example<S> example) {
        return productRepository.findOne(example);
    }

    public <S extends Products> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productRepository.findAll(example, pageable);
    }

    public <S extends Products> long count(Example<S> example) {
        return productRepository.count(example);
    }

    public <S extends Products> boolean exists(Example<S> example) {
        return productRepository.exists(example);
    }

    public <S extends Products, R> R findBy(Example<S> example,
            Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return productRepository.findBy(example, queryFunction);
    }

    @Override
    public int hashCode() {
        return productRepository.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return productRepository.equals(obj);
    }

    @Override
    public String toString() {
        return productRepository.toString();
    }

    public List<ProductDto> getListCategory(int id) {
        Connection conn = DBProvider.getConnection();
        List<ProductDto> ListCat = new ArrayList<ProductDto>();
        try {
            String sql = "SELECT * FROM product where category_id =  ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDto posts = new ProductDto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4),
                        rst.getDate(5), rst.getBoolean(6), rst.getString(7), rst.getInt(8));
                ListCat.add(posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDto> search(String name) {
        Connection conn = DBProvider.getConnection();
        List<ProductDto> ListCat = new ArrayList<ProductDto>();
        try {
            String sql = "SELECT * FROM product where name like  ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDto posts = new ProductDto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4),
                        rst.getDate(5), rst.getBoolean(6), rst.getString(7), rst.getInt(8));
                ListCat.add(posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDto> getListSortAsc() {
        Connection conn = DBProvider.getConnection();
        List<ProductDto> ListCat = new ArrayList<ProductDto>();
        try {
            String sql = "SELECT * FROM product order by price asc";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDto posts = new ProductDto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4),
                        rst.getDate(5), rst.getBoolean(6), rst.getString(7), rst.getInt(8));
                ListCat.add(posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDto> getListSortDesc() {
        Connection conn = DBProvider.getConnection();
        List<ProductDto> ListCat = new ArrayList<ProductDto>();
        try {
            String sql = "SELECT * FROM product order by price desc";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDto posts = new ProductDto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4),
                        rst.getDate(5), rst.getBoolean(6), rst.getString(7), rst.getInt(8));
                ListCat.add(posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductDto> getListSortName() {
        Connection conn = DBProvider.getConnection();
        List<ProductDto> ListCat = new ArrayList<ProductDto>();
        try {
            String sql = "SELECT * FROM product order by name";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                ProductDto posts = new ProductDto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4),
                        rst.getDate(5), rst.getBoolean(6), rst.getString(7), rst.getInt(8));
                ListCat.add(posts);
            }
            return ListCat;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

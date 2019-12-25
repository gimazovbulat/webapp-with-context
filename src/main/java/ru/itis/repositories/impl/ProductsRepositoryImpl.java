package ru.itis.repositories.impl;

import lombok.Setter;
import ru.itis.models.Product;
import ru.itis.repositories.interfaces.ProductsRepository;
import ru.itis.repositories.interfaces.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class ProductsRepositoryImpl implements ProductsRepository {
    @Setter
    private Connection connection;

    private RowMapper<Product> rowMapper = row -> Product.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .price(row.getFloat("price"))
            .build();


    @Override
    public void save(Product product) {

    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, price FROM simple_schema.products";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet row = ps.executeQuery();
            while (row.next()) {
                products.add(rowMapper.maprow(row));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        if (products.size() == 0) return Collections.emptyList();
        return products;
    }
}

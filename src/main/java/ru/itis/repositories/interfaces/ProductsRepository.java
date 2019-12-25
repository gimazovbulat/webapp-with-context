package ru.itis.repositories.interfaces;

import javalab.di.Component;
import ru.itis.models.Product;

import java.sql.Connection;
import java.util.List;

public interface ProductsRepository extends CrudRepository<Product, Long>, Component {
    List<Product> findAll();
    void setConnection(Connection connection);
}

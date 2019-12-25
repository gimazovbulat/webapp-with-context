package ru.itis.services.impl;

import lombok.Getter;
import ru.itis.dto.ProductDto;
import ru.itis.models.Product;
import ru.itis.repositories.interfaces.ProductsRepository;
import ru.itis.services.interfaces.ProductsService;

import java.util.ArrayList;
import java.util.List;

public class ProductsServiceImpl implements ProductsService {
    @Getter
    private ProductsRepository productsRepository;

    @Override
    public List<ProductDto> getProducts() {
        List<ProductDto> products = new ArrayList<>();
        for (Product p : productsRepository.findAll()) {
            products.add(
              ProductDto.builder()
              .name(p.getName())
              .price(p.getPrice())
              .build()
            );
        }
        System.out.println(products);
        return products;
    }
}

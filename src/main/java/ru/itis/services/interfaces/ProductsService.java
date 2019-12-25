package ru.itis.services.interfaces;

import javalab.di.Component;
import ru.itis.dto.ProductDto;
import ru.itis.repositories.interfaces.ProductsRepository;

import java.util.List;

public interface ProductsService  extends Component {
    List<ProductDto> getProducts();
    ProductsRepository getProductsRepository();
}

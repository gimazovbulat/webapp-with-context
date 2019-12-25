package ru.itis.controllers;

import javalab.di.web.Controller;
import javalab.di.web.Mapping;
import ru.itis.dto.ProductDto;
import ru.itis.services.interfaces.ProductsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Mapping(url = "/products")
public class ProductsController implements Controller {
    private ProductsService productsService;

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) {
        String method = req.getMethod();
        try {
            if (method.equals("GET")) {
                doGet(req, resp);
            } else doPost(req, resp);

        } catch (ServletException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
    private void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<ProductDto> products = productsService.getProducts();
//            resp.getWriter().print(mapper.writeValueAsString(products));
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/templates/products.ftl").forward(req, resp);
    }

    private void doPost(HttpServletRequest req, HttpServletResponse resp){

    }
}

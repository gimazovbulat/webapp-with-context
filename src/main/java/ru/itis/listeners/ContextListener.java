package ru.itis.listeners;

import javalab.di.ApplicationContext;
import javalab.di.ApplicationContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.config.DbConnectionManager;
import ru.itis.services.interfaces.ProductsService;
import ru.itis.services.interfaces.SignInService;
import ru.itis.services.interfaces.SignUpService;
import ru.itis.services.interfaces.UsersService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class ContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = new ApplicationContextImpl("ru.itis");
        applicationContext.createComponents();
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("applicationContext", applicationContext);
        DbConnectionManager connectionManager;
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //dao
        String username = ctx.getInitParameter("username");
        String url = ctx.getInitParameter("url");
        String password = ctx.getInitParameter("password");
        //setting connection, encoder
        SignUpService signUpService = applicationContext.getComponent(SignUpService.class);
        SignInService signInService = applicationContext.getComponent(SignInService.class);
        ProductsService productsService = applicationContext.getComponent(ProductsService.class);
        UsersService usersService = applicationContext.getComponent(UsersService.class);

        applicationContext.getComponent(SignInService.class).setEncoder(encoder);
        applicationContext.getComponent(SignUpService.class).setEncoder(encoder);
        try {
            connectionManager = new DbConnectionManager(username, password, url);
            ctx.setAttribute("connection", connectionManager.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        signUpService.getUsersRepository().setConnection(connectionManager.getConnection());
        signInService.getUsersRepository().setConnection(connectionManager.getConnection());
        productsService.getProductsRepository().setConnection(connectionManager.getConnection());
        usersService.getUsersRepository().setConnection(connectionManager.getConnection());
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
        try {
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

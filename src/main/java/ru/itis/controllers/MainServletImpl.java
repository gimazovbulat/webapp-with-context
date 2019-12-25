package ru.itis.controllers;

import javalab.di.ApplicationContext;
import javalab.di.web.Controller;
import javalab.di.web.MainServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class MainServletImpl extends HttpServlet implements MainServlet {
    Map<String, Class<Controller>> controllers;
    ApplicationContext applicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        applicationContext = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");
        controllers = applicationContext.getControllers();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        System.out.println("mainServlet " + requestURI);
            if (controllers.get(requestURI) != null) {
                Controller controller = applicationContext.getComponent(controllers.get(requestURI));
                if (controller != null) {
                    controller.handle(req, resp);
                }
        }
    }
}

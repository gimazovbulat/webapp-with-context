package ru.itis.controllers;

import javalab.di.web.Controller;
import javalab.di.web.Mapping;
import ru.itis.dto.UserDto;
import ru.itis.services.interfaces.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Mapping(url = "/profile")
public class ProfileController implements Controller {
    private UsersService usersService;

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
        HttpSession session = req.getSession();
        UserDto user = usersService.getUser((Long) session.getAttribute("user"));
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/templates/profile.ftl").forward(req, resp);
    }

    private void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }

}

package ru.itis.controllers;

import javalab.di.web.Controller;
import javalab.di.web.Mapping;
import ru.itis.dto.SignInFormDto;
import ru.itis.dto.UserDto;
import ru.itis.services.interfaces.SignInService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Mapping(url = "/signIn")
public class SignInController implements Controller {
    private SignInService signInService;

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

    private void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/templates/signIn.ftl").forward(req, resp);
    }

    private void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDto userDto = signInService.signIn(
                SignInFormDto.builder()
                        .email(email)
                        .password(password)
                        .build()
        );
        HttpSession session = req.getSession();
        session.setAttribute("user", userDto.getId());
        resp.sendRedirect("/profile");
    }
}

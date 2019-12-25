package ru.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javalab.di.web.Controller;
import javalab.di.web.Mapping;
import ru.itis.dto.SignUpFormDto;
import ru.itis.services.interfaces.SignUpService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Mapping(url = "/signUp")
public class SignUpController implements Controller {
    private SignUpService signUpService;

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

    private void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        signUpService.signUp(
                SignUpFormDto.builder()
                        .email(email)
                        .nickname(nickname)
                        .password(password)
                        .build()
        );
        resp.sendRedirect("/signIn");
    }

    private void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/templates/signUp.ftl").forward(req, resp);
    }
}

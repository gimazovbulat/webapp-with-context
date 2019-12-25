package ru.itis.services.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpFormDto;
import ru.itis.models.User;
import ru.itis.repositories.interfaces.UsersRepository;
import ru.itis.services.interfaces.SignUpService;

public class SignUpServiceImpl implements SignUpService {
    @Getter
    private UsersRepository usersRepository;
    @Setter
    private PasswordEncoder encoder;


    @Override
    public void signUp(SignUpFormDto dto) {
        System.out.println("from service " + this);
        System.out.println(dto);
        usersRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(encoder.encode(dto.getPassword()))
                        .nickname(dto.getNickname())
                        .build()
        );
    }

    @Override
    public String toString() {
        return "SignUpServiceImpl{" +
                "usersRepository=" + usersRepository +
                ", encoder=" + encoder +
                '}';
    }
}

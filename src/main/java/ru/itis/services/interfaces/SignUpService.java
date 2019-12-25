package ru.itis.services.interfaces;

import javalab.di.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpFormDto;
import ru.itis.repositories.interfaces.UsersRepository;

public interface SignUpService extends Component {
    void signUp(SignUpFormDto dto);
    void setEncoder(PasswordEncoder encoder);
    UsersRepository getUsersRepository();
}

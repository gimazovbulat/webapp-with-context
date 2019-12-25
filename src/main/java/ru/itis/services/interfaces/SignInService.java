package ru.itis.services.interfaces;

import javalab.di.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignInFormDto;
import ru.itis.dto.UserDto;
import ru.itis.repositories.interfaces.UsersRepository;

public interface SignInService extends Component {
    UserDto signIn(SignInFormDto signInFormDto);
    void setEncoder(PasswordEncoder encoder);
    UsersRepository getUsersRepository();
}

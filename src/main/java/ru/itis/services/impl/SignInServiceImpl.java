package ru.itis.services.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignInFormDto;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.interfaces.UsersRepository;
import ru.itis.services.interfaces.SignInService;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {
    @Getter
    private UsersRepository usersRepository;
    @Setter
    private PasswordEncoder encoder;

    @Override
    public UserDto signIn(SignInFormDto signInFormDto) {
        Optional<User> optionalUser = usersRepository.findByEmail(signInFormDto.getEmail());
        User user;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            if (encoder.matches(signInFormDto.getPassword(), user.getPassword())) {
                return UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .build();
            }
        }
        throw new IllegalStateException();
    }
}

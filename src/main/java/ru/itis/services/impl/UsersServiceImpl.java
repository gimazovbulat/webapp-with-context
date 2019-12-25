package ru.itis.services.impl;

import lombok.Getter;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.interfaces.UsersRepository;
import ru.itis.services.interfaces.UsersService;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    @Getter
    private UsersRepository usersRepository;


    @Override
    public UserDto getUser(Long id) {
        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserDto.builder()
                    .id(id)
                    .email(user.getEmail())
                    .nickname(user.getNickname())
                    .build();
        }
        throw new NoSuchElementException();
    }
}

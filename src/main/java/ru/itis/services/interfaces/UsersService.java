package ru.itis.services.interfaces;

import javalab.di.Component;
import ru.itis.dto.UserDto;
import ru.itis.repositories.interfaces.UsersRepository;

public interface UsersService extends Component {
   UserDto getUser(Long id);
   UsersRepository getUsersRepository();
}

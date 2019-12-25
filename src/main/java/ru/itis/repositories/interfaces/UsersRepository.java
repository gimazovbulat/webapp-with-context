package ru.itis.repositories.interfaces;

import javalab.di.Component;
import ru.itis.models.User;

import java.sql.Connection;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long>, Component {
    void setConnection(Connection connection);
    Optional<User> findByEmail(String email);
}

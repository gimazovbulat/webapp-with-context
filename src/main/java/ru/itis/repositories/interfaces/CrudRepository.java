package ru.itis.repositories.interfaces;

import java.util.Optional;

public interface CrudRepository<T, ID> {
    void save(T t);
    Optional<T> findById(ID id);
    void delete(ID id);
    void update(T t);
}

package ru.itis.repositories.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T maprow(ResultSet row) throws SQLException;
}

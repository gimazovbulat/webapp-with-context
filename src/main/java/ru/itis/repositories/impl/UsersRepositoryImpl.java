package ru.itis.repositories.impl;

import lombok.Setter;
import ru.itis.models.User;
import ru.itis.repositories.interfaces.RowMapper;
import ru.itis.repositories.interfaces.UsersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    @Setter
    private Connection connection;

    private RowMapper<User> rowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .email(row.getString("email"))
            .nickname(row.getString("nickname"))
            .password(row.getString("password"))
            .build();

    @Override
    public void save(User user) {
        String sql = "INSERT INTO simple_schema.users (email, password, nickname)" +
                " VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) throw new SQLException();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        String sql = "SELECT id, email, password, nickname FROM simple_schema.users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user = rowMapper.maprow(rs);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, email, nickname, password FROM simple_schema.users WHERE email = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return Optional.ofNullable(rowMapper.maprow(rs));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }
}

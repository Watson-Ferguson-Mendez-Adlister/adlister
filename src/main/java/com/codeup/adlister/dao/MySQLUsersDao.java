package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }


    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            return extractUser(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Email already in use!",e);
        }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        if(findByUsername(user.getUsername()) == null) {
            try {
                PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                return rs.getLong(1);
            } catch (SQLException e) {
                throw new RuntimeException("Error creating new user", e);
            }
        }
        return null;
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?"
            );

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword()); // new line
            stmt.setLong(4, user.getId()); // change from 3 to 4

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating user.", e);
        }
    }


    @Override
    public void delete(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM users WHERE id = ?"
            );

            stmt.setLong(1, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user.", e);
        }
    }

    @Override
    public User findById(long id) {
        String query = "SELECT * FROM users WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1, id);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by id", e);
        }
    }

    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
        );
    }

}

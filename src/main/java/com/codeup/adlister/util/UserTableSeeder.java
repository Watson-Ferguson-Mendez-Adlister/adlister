package com.codeup.adlister.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserTableSeeder {

    // Database credentials and connection information
    private static final String DB_URL = "jdbc:mysql://localhost:3306/adlister_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "codeup";

    public static void main(String[] args) {
        seedUsersTable();
    }

    public static void seedUsersTable() {
        // Sample user data
        String[] usernames = { "john_doe", "jane_smith", "alex123", "brenden1", "tiffani1", "robert1" };
        String[] emails = { "john.doe@example.com", "jane.smith@example.com", "alex@example.com", "brenden@codeup.com", "tiffani@codeup.com", "robert@codeup.com"};
        String[] passwords = { "pass123", "secret456", "qwerty789", "pass", "pass", "pass" };

        String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (int i = 0; i < usernames.length; i++) {
                preparedStatement.setString(1, usernames[i]);
                preparedStatement.setString(2, emails[i]);
                preparedStatement.setString(3, passwords[i]);
                preparedStatement.executeUpdate();
            }

            System.out.println("Data successfully seeded!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


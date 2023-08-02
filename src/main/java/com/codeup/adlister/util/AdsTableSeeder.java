package com.codeup.adlister.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdsTableSeeder {

    // Database credentials and connection information
    private static final String DB_URL = "jdbc:mysql://localhost:3306/adlister_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "codeup";

    public static void main(String[] args) {
        seedAdsTable();
    }

    public static void seedAdsTable() {
        // Sample ad data
        String[] titles = { "Car for Sale", "Apartment for Rent", "Job Opportunity" };
        String[] descriptions = {
                "Selling a well-maintained used car. Contact for more details.",
                "Spacious apartment available for rent. Great location!",
                "We are hiring software developers. Apply now!"
        };
        String[] categories = { "forSale", "housing", "jobs" };
        long[] userIds = { 1L, 2L, 3L }; // Assuming user IDs exist in the 'users' table

        String insertQuery = "INSERT INTO ads (user_id, title, description, category) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            for (int i = 0; i < titles.length; i++) {
                preparedStatement.setLong(1, userIds[i]);
                preparedStatement.setString(2, titles[i]);
                preparedStatement.setString(3, descriptions[i]);
                preparedStatement.setString(4, categories[i]);
                preparedStatement.executeUpdate();
            }

            System.out.println("Data successfully seeded!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

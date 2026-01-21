package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class config {

    // ⚠️ CHANGE THIS to the FULL path of your database file
    private static final String DB_URL =
            "jdbc:sqlite:C:/FULL/PATH/TO/Clothing_db.db";

    public static Connection getConnection() {
        try {
            // Load SQLite JDBC driver (important for NetBeans + Java 8)
            Class.forName("org.sqlite.JDBC");

            Connection conn = DriverManager.getConnection(DB_URL);
            System.out.println("✅ Database connected successfully");
            return conn;

        } catch (ClassNotFoundException e) {
            System.out.println("❌ SQLite JDBC Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to database!");
            e.printStackTrace();
        }

        return null;
    }
}


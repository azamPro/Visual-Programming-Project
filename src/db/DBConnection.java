package db;
import java.sql.*;

public class DBConnection {
    public static Connection connect() {
        try {
            String url = "jdbc:sqlite:data/events.db";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("DB connection failed: " + e.getMessage());
            return null;
        }
    }
}

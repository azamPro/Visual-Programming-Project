package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/events_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // // for testing purposes only
    // public static Connection connect() {
    //     try {
    //         // Class.forName("com.mysql.cj.jdbc.Driver");
    //         return DriverManager.getConnection(URL, USER, PASSWORD);
    //     } catch (SQLException e) {
    //         System.out.println("❌ Database connection failed: " + e.getMessage());
    //         return null;
    //     }
    // }
    // for real work
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

       public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("✅ Connected to MySQL!");
            } else {
                System.out.println("❌ Connection failed.");
            }
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        }
    }

    // example to use the connection and test it with insert and select
//    public static void main(String[] args) {
//         try {
//             Connection conn = getConnection();

//             if (conn != null) {
//                 System.out.println("✅ Connected to MySQL!");

//                 // ✅ INSERT a new user
//                 String insertSQL = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
//                 PreparedStatement insertStmt = conn.prepareStatement(insertSQL);
//                 insertStmt.setString(1, "testuser3");
//                 insertStmt.setString(2, "tes12t@example.com");
//                 insertStmt.setString(3, "123456");
//                 insertStmt.executeUpdate();
//                 insertStmt.close();
//                 System.out.println("✅ Sample user inserted!");

//                 // ✅ SELECT and print all users
//                 String selectSQL = "SELECT * FROM users";
//                 Statement selectStmt = conn.createStatement();
//                 ResultSet rs = selectStmt.executeQuery(selectSQL);

//                 System.out.println("📋 All users:");
//                 while (rs.next()) {
//                     int id = rs.getInt("user_id");
//                     String username = rs.getString("username");
//                     String email = rs.getString("email");
//                     String password = rs.getString("password");
//                     System.out.println(id + " | " + username + " | " + email + " | " + password);
//                 }

//                 rs.close();
//                 selectStmt.close();
//                 conn.close();
//             } else {
//                 System.out.println("❌ Connection failed.");
//             }

//         } catch (SQLException e) {
//             System.out.println("❌ SQL Error: " + e.getMessage());
//         }
//     }

}
package objprog;

import java.sql.SQLException;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.Random;

public class DerbyTest {
    private static final String DERBY_SYSTEM_HOME = System.getenv("DERBY_SYSTEM_HOME");
    private static final String DB_URL = "jdbc:derby://localhost:45270/myDB;create=true";

    //private static final String DB_URL = "jdbc:derby:"+ DERBY_SYSTEM_HOME +"/myDB;create=true";
    private static final String[] NAMES = {"Alice", "Bob", "Charlie", "David", "Eve"};

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            System.out.println("Connected to Derby database.");
            System.out.println(conn);
            createTable(conn);
            insertData(conn);
            fetchData(conn);
            removeRandomData(conn);
            fetchData(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void removeRandomData(Connection conn) {
        try {
            // Count total rows
            String countSQL = "SELECT COUNT(*) FROM Users";
            int rowCount = 0;
            try (Statement countStmt = conn.createStatement(); ResultSet countRs = countStmt.executeQuery(countSQL)) {
                if (countRs.next()) {
                    rowCount = countRs.getInt(1);
                }
            }

            if (rowCount == 0) {
                System.out.println("No users to remove.");
                return;
            }

            // Pick a random row index
            Random random = new Random();
            int randomIndex = random.nextInt(rowCount);

            // Select the user at that index
            String selectSQL = "SELECT ID, NAME FROM Users FETCH FIRST ? ROWS ONLY";
            int id = -1;
            String name = null;
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL)) {
                selectStmt.setInt(1, randomIndex + 1);
                try (ResultSet rs = selectStmt.executeQuery()) {
                    while (rs.next()) { // Fetch last row of the limited set
                        id = rs.getInt("ID");
                        name = rs.getString("NAME");
                    }
                }
            }

            if (id == -1) {
                System.out.println("Failed to select a user to remove.");
                return;
            }

            // Delete the selected user
            String deleteSQL = "DELETE FROM Users WHERE ID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Removed user: ID=" + id + ", Name=" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String createTableSQL = "CREATE TABLE Users (ID INT PRIMARY KEY, NAME VARCHAR(100))";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'Users' created.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) { // Table already exists
                System.out.println("Table already exists.");
            } else {
                throw e;
            }
        }
    }

    private static void insertData(Connection conn) throws SQLException {
        String insertSQL = "INSERT INTO Users (ID, NAME) VALUES (?, ?)";
        Random random = new Random();
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            for (int i = 1; i <= 5; i++) {
                pstmt.setInt(1, i);
                pstmt.setString(2, NAMES[random.nextInt(NAMES.length)]);
                try {
                    pstmt.executeUpdate();
                    System.out.println("Inserted data: ID=" + i);
                } catch (SQLException e) {
                    if (e.getSQLState().equals("23505")) { // Duplicate primary key
                        System.out.println("Data for ID=" + i + " already exists.");
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

    private static void fetchData(Connection conn) throws SQLException {
        String selectSQL = "SELECT * FROM Users";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectSQL)) {
            System.out.println("Users table:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID") + ", Name: " + rs.getString("NAME"));
            }
        }
    }
}

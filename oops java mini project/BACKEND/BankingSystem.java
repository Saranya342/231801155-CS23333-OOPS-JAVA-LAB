import java.sql.*;

public class BankingSystem {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE"; // Update this
    private static final String USER = "system"; // Update with your DB username
    private static final String PASS = "saranya155"; // Update with your DB password

    public void createAccount(String name) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO accounts (account_name, balance) VALUES (?, 0)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            }
        }
    }

    public void deposit(String name, double amount) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE accounts SET balance = balance + ? WHERE account_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, amount);
                pstmt.setString(2, name);
                pstmt.executeUpdate();
            }
        }
    }

    public void withdraw(String name, double amount) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "UPDATE accounts SET balance = balance - ? WHERE account_name = ? AND balance >= ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDouble(1, amount);
                pstmt.setString(2, name);
                pstmt.setDouble(3, amount);
                pstmt.executeUpdate();
            }
        }
    }

    public double checkBalance(String name) throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT balance FROM accounts WHERE account_name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getDouble("balance");
                } else {
                    throw new SQLException("Account not found.");
                }
            }
        }
    }
}

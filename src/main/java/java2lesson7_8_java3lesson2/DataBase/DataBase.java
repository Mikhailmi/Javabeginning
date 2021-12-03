package java2lesson7_8_java3lesson2.DataBase;

import java2lesson7_8_java3lesson2.server.AuthService;

import java.sql.*;

public class DataBase {

    private static Connection connection;
    private static Statement stmt;


    public static void main(String[] args) {
        try {
            connect();
            createTableEx();
            insertAuthBatch();
            readData();
                //   dropTableEx();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {

        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void createTableEx() throws SQLException {
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS authService (\n" +
                "        login TEXT not null,\n" +
                "        password TEXT not null,\n" +
                "        nick TEXT NOT NULL\n" +
                "    );");
    }

    private static void dropTableEx() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS authService;");
    }

    private static void clearTableEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM authService;");
    }


    private static void insertAuthBatch() {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into AuthService (login, password, nick) " +
                        "values (?, ?, ?)")) {
            for (int i = 1; i < 4; i++) {
                ps.setString(1, "login" + i);
                ps.setString(2, "pass" + i);
                ps.setString(3, "nick" + i);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void readData() throws SQLException {
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM authService;")) {
            while (rs.next()) {
                System.out.println(rs.getString("login") + " " + rs.getString("password") +
                        " " + rs.getString("nick"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    }
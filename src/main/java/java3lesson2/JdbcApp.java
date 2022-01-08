package java3lesson2;

import java.sql.*;
import java.util.Random;

public class JdbcApp {

    private static Connection connection;
    private static Statement stmt;
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            connect();
            createTableEx();
            long start = System.currentTimeMillis();
            insertStudents();
            System.out.println("insert stmt " + (System.currentTimeMillis() - start) + "ms");
            start = System.currentTimeMillis();
            insertStudentsBatch();
            System.out.println("insert batch " + (System.currentTimeMillis() - start) + "ms");

            insertOneStudent("'Charlie'", "34");
            readData();
            dropTableEx();
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
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (\n" +
                "        id    INTEGER PRIMARY KEY AUTOINCREMENT not null,\n" +
                "        name  TEXT not null,\n" +
                "        group_name  TEXT,\n" +
                "        score INTEGER\n" +
                "    );");
    }

    private static void dropTableEx() throws SQLException {
        stmt.executeUpdate("DROP TABLE IF EXISTS students;");
    }

    private static void readData() throws SQLException {
        try (ResultSet rs = stmt.executeQuery("SELECT * FROM students;")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") +
                        " " + rs.getInt(3));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void clearTableEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM students;");
    }

    private static void insertStudents() throws SQLException {
        for (int i =0; i<1000; i++) {
            stmt.executeUpdate(
                    "insert into students (name, group_name, score)" +
                    "values ('Bob" + i + "', '22', 3)");
        }
    }

    private static void insertStudentsBatch() {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into students (name, group_name, score) " +
                        "values (?, ?, ?)")) {
            for (int i = 0; i < 1000; i++) {
                ps.setString(1, "Jack " + i);
                ps.setString(2, "group " + (10 - i));
                ps.setInt(3, random.nextInt(6));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


       private static void insertOneStudent(String name, String group) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into students (name, group_name, score) " +
                        "values (?, ?, 3)")) {
            ps.setString(1, name);
            ps.setString(2, group);
            ps.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void deleteEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM students WHERE name = 'Bob1';");
    }

    private static void updateEx() throws SQLException {
        stmt.executeUpdate("UPDATE students SET score = 100 WHERE name = 'Bob4';");
    }

    private static void insertEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob4', 60);");
    }




}

package java2lesson7_8_java3lesson2_3.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


 public class BaseAuthService implements AuthService {
     private static Statement stmt;
     private List<Entry> entries;
     private static Connection connection;

     public static void connect() throws SQLException {
         connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
         stmt = connection.createStatement();
     }

     public class Entry {
         private String login;
         private String password;
         private String nick;

         public Entry(String login, String password, String nick) {
             this.login = login;
             this.password = password;
             this.nick = nick;
         }


     }


     @Override
     public void start() {
         System.out.println("Auth service is running");
     }

     @Override
     public void stop() {
         System.out.println("Auth service is shutting down");
     }


     public BaseAuthService() {
         entries = new ArrayList<>();
        /* entries.add(new Entry("login1", "pass1", "nick1"));
         entries.add(new Entry("login2", "pass2", "nick2"));
         entries.add(new Entry("login3", "pass3", "nick3"));*/

         try {
             connect();
             ResultSet rs = stmt.executeQuery("SELECT * FROM authService");
             while (rs.next()) {
                  entries.add(new Entry(rs.getString("login"), rs.getString("password"), rs.getString("nick")));
             }
         } catch (Exception ex) {
             ex.printStackTrace();
         }
     }

         @Override
         public String getNickByLoginPass(String login, String pass) {
             for (Entry entry : entries) {
                 if (entry.login.equals(login) && entry.password.equals(pass)) return entry.nick;
             }
             return null;
         }

/* return entries.stream()
                 .filter(entry -> entry.login.equals(login) && entry.password.equals(pass))
                 .map(entry -> entry.nick)
                 .findFirst();

         */


     }

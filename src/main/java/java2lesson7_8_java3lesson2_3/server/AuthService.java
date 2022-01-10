package java2lesson7_8_java3lesson2_3.server;

import java.sql.SQLException;

/**
 * сервис аутентификации
 */

public interface AuthService {

    /**
     * Запустить сервис
     */
    void start();

    /**
     * Получить никнейм по логину/паролю
     * @param login
     * @param pass
     * @return
     */

   String getNickByLoginPass(String login, String pass) throws SQLException;
       /**
     * Отключить сервис
     */
    void stop();




}
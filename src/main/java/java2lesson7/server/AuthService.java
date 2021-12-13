package java2lesson7.server;

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
     * @return никнейм если найден или null, если такого нет
     */

    String getNickByLoginPass(String login, String pass);

       /**
     * Отключить сервис
     */
    void stop();




}
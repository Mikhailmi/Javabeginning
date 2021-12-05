package java2lesson7_8_java3lesson2_3.server;

/**
* Логика сервера

 */

import java2lesson7_8_java3lesson2_3.constants.Constants;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyServer {


    /**
     * Активные клиенты
     */

    public List<ClientHandler> clients;

    /**
     * Сервис аутентификации
     */
    private AuthService authService;
    private File serverHistory;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(Constants.SERVER_PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);

                File serverDir = new File ("src/main/java/java2lesson7_8_java3lesson2_3/server");
                if (!serverDir.exists()) {
                    serverDir.mkdir();
                }
                serverHistory = new File (serverDir, "server_history.txt");

                if (!serverHistory.exists()){
                    serverHistory.createNewFile();
                }
            }
        } catch (IOException ex) {
            System.out.println("Ошибка в работе сервера");
            ex.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void broadcastMsg(String msg) {

       // clients.forEach(client -> client.sendMsg(msg));


        for (ClientHandler client : clients) {
            client.sendMsg(msg);
            if (!(serverHistory == null)) {
                byte [] outData = (msg + "\n").getBytes();

                /**
                 * здесь записываем данные в историю сервера
                 */
                try {
                    FileOutputStream out = new FileOutputStream(serverHistory, true);
                    out.write(outData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

       }
    }


    public synchronized void privateOutMsg (ClientHandler from, String nickTo, String msg) {

        for (ClientHandler o: clients) {
            if (o.getNick().equals(nickTo)) {
            o.sendMsg("от " + from.getNick() + ": " + msg);
            from.sendMsg("к " + nickTo + " Вы отправили: " + msg);
            return;
            }
        }
        from.sendMsg("Nick: " + nickTo + " не найден в чате");
    }




    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }


    public synchronized String getActiveClients() {
        StringBuilder sb = new StringBuilder(Constants.CLIENTS_LIST_COMMAND).append(" ");
        sb.append(clients.stream()
                .map(c -> c.getName())
        .collect(Collectors.joining(" "))
        );
        /* for (ClientHandler clientHandler : clients) {
            sb.append(clientHandler.getNick()).append(" ");

        }*/
        return sb.toString();

    }
}

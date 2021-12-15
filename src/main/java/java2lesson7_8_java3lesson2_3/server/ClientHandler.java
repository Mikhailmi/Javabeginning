package java2lesson7_8_java3lesson2_3.server;

import java2lesson7_8_java3lesson2_3.constants.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Обработчик для конкретного клиента
 */

public class ClientHandler {
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean isAuthorized = false;
    private static Statement stmt;

    private String name;


    public String getName() {
        return name;
    }

    public ClientHandler(MyServer server, Socket socket) {
        try {
           // new Thread(() -> { // Задание: заменить все серверные треды на экзекьютор сервис - создать пул потоков и потом его переиспользовать
                // те ранаблы, которые пихали в треды, отправить в экзекутор сервис. Могут понадобиться гетеры для экзекьютор сервис


            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(() -> {

                try {
                    while (true) {


                        if (isAuthorized == false) {
                            Thread.sleep(120000);
                            if (isAuthorized == false) {
                                closeConnection();
                                break;
                            }

                        }

                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }); // .start();
            executorService.shutdown();

            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException | SQLException ex) {
                    ex.printStackTrace();
                    server.logger.info("Произошла ошибка");
                } finally {
                    closeConnection();
                }
            }).start();


        } catch (IOException ex) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public String getNick() {

    return name;
}

    // auth login pass

    public synchronized void authentication() throws IOException, SQLException {
        while (true) {

                String str = in.readUTF();
            server.logger.info("Клиент авторизовался");
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+"); // пробел один или больше между словами и получаем массив длиной 3
                String nick = server.getAuthService().getNickByLoginPass(tokens[1], tokens[2]);
                if (nick != null) {
                    if (!server.isNickBusy(nick)) {
                        sendMsg(Constants.AUTH_OK_COMMAND + " " + nick);
                        name = nick;
                        server.broadcastMsg(name + " зашел в чат");
                        server.subscribe(this);
                        isAuthorized = true;


                            return;


                        } else {
                            sendMsg("Учетная запись уже используется");
                        }
                    } else {
                        sendMsg("Неверные логин/пароль");
                    }
                }

        }

    }

    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            server.logger.info("Клиент прислал сообщение/команду: " + strFromClient);

            if (strFromClient.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                sendMsg(server.getActiveClients());
            }
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals(Constants.END_COMMAND)) {
                return;
            }
            if (strFromClient.startsWith(Constants.PRIVATE_CHAT_COMMAND)) {
                String[] tokens = strFromClient.split("\\s+");
                server.privateOutMsg(ClientHandler.this, tokens[1], tokens[2]);


                }

            if (strFromClient.startsWith(Constants.CHANGE_NICK_COMMAND)) {
                String[] tokens = strFromClient.split("\\s+");
                String lastName = name;
                name = tokens[1];
                server.broadcastMsg(lastName + " изменил свой ник на: " + name);

            } else {
                server.broadcastMsg(name + ": " + strFromClient);

            }
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPrivateMsg (String privateMsg) {
        try {
            out.writeUTF(privateMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMsg(name + " вышел из чата");
        System.out.println("Соединение закрыто");

        try {
            in.close();
        } catch (IOException ex) {
          //ignore  ex.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException ex) {
          // ignore  ex.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException ex) {
           // ignore ex.printStackTrace();
        }
    }

}

package java2lesson7.server;

import java2lesson7.constants.Constants;

import javax.print.DocFlavor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

/**
 * Обработчик для конкретного клиента
 */

public class ClientHandler {
    private MyServer server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;


    public String getName() {
        return name;
    }

    public ClientHandler(MyServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException ex) {
                    ex.printStackTrace();
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

    public void authentication() throws IOException {
        while (true) {
            String str = in.readUTF();
            if (str.startsWith(Constants.AUTH_COMMAND)) {
                String[] tokens = str.split("\\s+"); // пробел один или больше между словами и получаем массив длиной 3
                String nick = server.getAuthService().getNickByLoginPass(tokens[1], tokens[2]);
                if (nick != null) {
                    if (!server.isNickBusy(nick)) {
                        sendMsg(Constants.AUTH_OK_COMMAND + nick);
                        name = nick;
                        server.broadcastMsg(name + " зашел в чат");
                        server.subscribe(this);
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
            System.out.println("от " + name + ": " + strFromClient);
            if (strFromClient.equals(Constants.END_COMMAND)) {
                return;
            }
            if (strFromClient.startsWith(Constants.PRIVATE_CHAT_COMMAND)) {
                String[] tokens = strFromClient.split("\\s+");
                server.privateOutMsg(ClientHandler.this, tokens[1], tokens[2]);

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

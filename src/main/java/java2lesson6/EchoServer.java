package java2lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8089)) { // try with resources
            System.out.println("Сервер ожидает подключения...");
            socket = serverSocket.accept(); // ждем подключения (БЛОКИРУЕМСЯ)
            System.out.println("Клиент подключился");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                while (true) {
                    Scanner sc = new Scanner(System.in);
                    String outPutMessage = sc.nextLine();
                    try {
                        dataOutputStream.writeUTF("Server: " + outPutMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {

                String message = dataInputStream.readUTF();
                // poison pill (ядовитая таблетка)

                if (message.equals("/end")){
                    dataOutputStream.writeUTF(message);
                    break;
                }

                System.out.println("Клиент прислал " + message);

            }

            } catch (IOException e){
            e.printStackTrace();
        } // finally {serverSocket.close()}

    }

}

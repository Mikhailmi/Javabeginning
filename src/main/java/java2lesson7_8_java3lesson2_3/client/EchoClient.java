package java2lesson7_8_java3lesson2_3.client;


import java2lesson7_8_java3lesson2_3.constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoClient extends JFrame {


        private JTextField textField;
        private JTextArea textArea;


        private Socket socket;
        private DataInputStream dataInputStream;
        private DataOutputStream dataOutputStream;
        private String login;
        private File clientHistory;


        public EchoClient() {
            try {

                openConnection();


            } catch (IOException e) {
                e.printStackTrace();
           }

            prepareUI();
        }

        private void openConnection () throws IOException  {
            socket = new Socket(Constants.SERVER_ADRESS, Constants.SERVER_PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String messageFromServer = dataInputStream.readUTF();
                        if (messageFromServer.equals("/end")) {
                            break;
                        }

                        if (messageFromServer.startsWith(Constants.AUTH_OK_COMMAND)) {

                            String[] tokens = messageFromServer.split("\\s+");
                            this.login = tokens[1];
                            textArea.append("Успешно авторизован как " + login);
                            textArea.append("\n");

                            /**
                             * Здесь создание пути и папки для записи истории клиента
                             */


                            File clientDir = new File ("src/main/java/java2lesson7_8_java3lesson2_3/client");
                            if (!clientDir.exists()) {
                                clientDir.mkdir();
                            }
                            clientHistory = new File (clientDir, "history_" + login + ".txt");
                            if (!clientHistory.exists()){
                                clientHistory.createNewFile();
                            }

                            /**
                             * Далее код для отображения последних 100 сообщений из истории чата                             *
                             */

                            File serverDir = new File ("src/main/java/java2lesson7_8_java3lesson2_3/server");
                            File serverHistory = new File (serverDir, "server_history.txt");
                            List<String> historyList = new ArrayList<>();
                            FileInputStream in = new FileInputStream(serverHistory);
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                            String temp;
                            while ((temp = bufferedReader.readLine()) != null) {
                                historyList.add(temp);
                            }
                            int outHistory = 100;
                            if (historyList.size() > outHistory) {
                                for (int i = historyList.size() - outHistory; i < historyList.size(); i++) {
                                   textArea.append(historyList.get(historyList.size() - i) + "\n");
                                }
                            } else {
                                for (int i = 0; i < historyList.size(); i++) {
                                    textArea.append(historyList.get(i) + "\n");
                                }
                            }

                        } else if (messageFromServer.startsWith(Constants.CLIENTS_LIST_COMMAND)) {
                            // список клиентов

                        } else {
                            textArea.append(messageFromServer);
                            textArea.append("\n");
                        }

                        /**
                         * Здесь код для записи истории конкретного клиента
                         */
                        if (!(clientHistory == null)) {
                            byte [] outData = (messageFromServer + "\n").getBytes();
                            try {
                                FileOutputStream out = new FileOutputStream(clientHistory, true);
                                out.write(outData);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    textArea.append("Соединение разорвано");
                    textField.setEnabled(false);
                    closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();



        }

        private void closeConnection () {
            try {

                dataOutputStream.close();
            } catch (Exception ex) {

            }
            try {
                dataInputStream.close();
            } catch (Exception ex) {

            }
            try {
                socket.close();
            } catch (Exception ex) {

            }
        }

        private void sendMessage () {
            if (textField.getText().trim().isEmpty()){
                return;
            }

                       try {
                dataOutputStream.writeUTF(textField.getText());

                textField.setText("");
                textField.grabFocus();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private void prepareUI(){
            setBounds (200, 200, 500, 500);
            setTitle("EchoClient");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            add(new JScrollPane(textArea), BorderLayout.CENTER);

            JPanel panel = new JPanel(new BorderLayout());
            JButton button = new JButton("Send");
            panel.add(button, BorderLayout.EAST);
            textField = new JTextField();
            panel.add(textField, BorderLayout.CENTER);

            add(panel, BorderLayout.SOUTH);

            JPanel loginPanel = new JPanel(new BorderLayout());
            JTextField loginField = new JTextField();
            loginPanel.add(loginField, BorderLayout.WEST);
            JTextField passField = new JTextField();
            loginPanel.add(passField, BorderLayout.CENTER);
            JButton authButton = new JButton("Авторизоваться");
            loginPanel.add(authButton, BorderLayout.EAST);
            add(loginPanel, BorderLayout.NORTH);

            authButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    try {
                        dataOutputStream.writeUTF(Constants.AUTH_COMMAND + " " + loginField.getText() + " " + passField.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });




            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   sendMessage();
                }
            });

            textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                sendMessage();
                }
            });
            setVisible(true);
        }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(EchoClient::new);


    }
}


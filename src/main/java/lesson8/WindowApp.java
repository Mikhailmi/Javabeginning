package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;


public class WindowApp extends JFrame {

   public WindowApp(){
       setTitle("My new title");
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       setBounds(300, 300, 400, 400);
     /*  setLayout(new GridLayout(3, 3));
       for (int i = 0; i < 9; i++) {
           JButton button = new JButton("Button #" + (i + 1));
           add(button);
       }*/

       /*JButton button = new JButton("Button #1");
       JButton button2 = new JButton("Button #2");
       add(button, BorderLayout.NORTH);
       add(button2, BorderLayout.SOUTH);*/

       setLayout(null);
       JTextField textField = new JTextField();
       textField.setBounds(20, 20, 120, 32);
       add(textField);
       JButton button = new JButton("Press here");
       button.setBounds(20, 80, 120, 32);
       add(button);

       button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               button.setText(textField.getText());
           }
       });

       textField.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               System.out.println(textField.getText());
               button.setText(textField.getText());
           }
       });

       addWindowListener(new WindowAdapter() {
                             @Override
                             public void windowClosing(WindowEvent e) {
                                 System.out.println("Window is closing");
                             }
                         }
       );




                       setVisible(true);
   }


       public static void main(String[] args) {
        new WindowApp();
    }
    }


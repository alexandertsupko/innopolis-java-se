/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework08.draft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Вспомогательный класс для создания у клиента графического интерфейса пользователя.
 *
 * @author Александр Цупко
 */
public class ClientGUI extends JFrame {
    private JTextArea jTextArea = null;
    private JTextField jTextField = null;
    private Socket socket = null;
    private Thread thread = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;

    protected ClientGUI() {
        // Создать окно
        this.setTitle("Чат");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Создать многострочное текстовое поле
        this.jTextArea = new JTextArea();
        this.jTextArea.setEditable(false);
        this.jTextArea.setLineWrap(true);

        // Обернуть многострочное текстовое поле в прокручиваемую область
        JScrollPane jScrollPane = new JScrollPane(this.jTextArea);
        this.add(jScrollPane, BorderLayout.CENTER);

        // Создать панель ввода и отправки сообщений
        JPanel jPanelTextField = new JPanel(new BorderLayout());

        this.jTextField = new JTextField();
        this.jTextField.addActionListener(event -> {
            if (!this.jTextField.getText().trim().isEmpty()) {
                this.sendMessage();
            } else {
                this.jTextField.setText("");
            }
        });

        JButton jButtonSend = new JButton("Отправить");
        jButtonSend.addActionListener(event -> {
            if (!this.jTextField.getText().trim().isEmpty()) {
                this.sendMessage();
            } else {
                this.jTextField.setText("");
            }
            this.jTextField.grabFocus();
        });

        jPanelTextField.add(this.jTextField, BorderLayout.CENTER);
        jPanelTextField.add(jButtonSend, BorderLayout.EAST);
        this.add(jPanelTextField, BorderLayout.SOUTH);

        // Обработать события, связанные с окном
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                jTextField.grabFocus();
                super.windowOpened(event);
            }

            @Override
            public void windowClosing(WindowEvent event) {
                super.windowClosing(event);
                if (socket != null) {
                    try {
                        dataOutputStream.writeUTF("end");
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        dataInputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // Отобразить окно
        this.setVisible(true);
    }

    protected void connect() {
        if (this.thread == null) {
            try {
                this.socket = new Socket("localhost", 3232);
                this.dataInputStream = new DataInputStream(socket.getInputStream());
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.thread = new Thread(() -> {
                try {
                    while (true) {
                        String message = this.dataInputStream.readUTF();
                        if ("end".equalsIgnoreCase(message)) {
                            break;
                        }
                        this.jTextArea.append(message + '\n');
                        this.jTextArea.setCaretPosition(this.jTextArea.getDocument().getLength());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    this.thread = null;
                    e.printStackTrace();
                }
            });
            this.thread.start();
        }
    }

    /**
     * Отправляет сообщение из однострочного текстового поля в выходной поток сокета
     * по нажатию клавиши Enter или по нажатию кнопки "Отправить",
     * и делает однострочное текстовое поле пустым.
     */
    private void sendMessage() {
        if (this.thread != null) {
            try {
                this.dataOutputStream.writeUTF(this.jTextField.getText());
                this.dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.jTextField.setText("");
    }

    public static void main(String[] args) {
//        SwingUtilities.invokeLater(Client::new);
    }
}

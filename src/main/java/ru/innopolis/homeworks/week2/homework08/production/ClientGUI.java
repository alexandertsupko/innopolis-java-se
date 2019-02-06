/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework08.production;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

/**
 * Вспомогательный класс для создания у клиента графического интерфейса пользователя.
 *
 * @author Александр Цупко
 */
public class ClientGUI extends JFrame {
    private static final String STOP_MESSAGE = "quit";
    private static final String HOST = "localhost";
    private static final int PORT = 3232;

    private JTextArea jTextArea;
    private JTextField jTextField;

    private Socket socket = null;
    private DataInputStream reader = null;
    private DataOutputStream writer = null;

    public ClientGUI() {
        try {
            this.socket = new Socket(HOST, PORT);
            this.reader = new DataInputStream(this.socket.getInputStream());
            this.writer = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setTitle("Client");
        this.setBounds(800,400, 400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.jTextArea = new JTextArea();
        this.jTextArea.setEditable(false);
        this.jTextArea.setLineWrap(true);

        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        this.add(jScrollPane, BorderLayout.CENTER);

        JPanel jPanelMessage = new JPanel(new BorderLayout());

        this.jTextField = new JTextField();
        this.jTextField.addActionListener(event -> sendMessage());

        JButton jButtonSend = new JButton("Send");
        jButtonSend.addActionListener(event -> sendMessage());

        jPanelMessage.add(this.jTextField, BorderLayout.CENTER);
        jPanelMessage.add(jButtonSend, BorderLayout.EAST);
        this.add(jPanelMessage, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                super.windowOpened(event);
                jTextField.grabFocus();
            }

            @Override
            public void windowClosing(WindowEvent event) {
                super.windowClosing(event);
                try {
                    if (!socket.isClosed()) {
                        writer.writeUTF(STOP_MESSAGE);
                        writer.flush();
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.setVisible(true);

        new Thread(this::run).start();
    }

    private void sendMessage() {
        if (!this.socket.isClosed() && !this.jTextField.getText().trim().isEmpty()) {
            try {
                this.writer.writeUTF(this.jTextField.getText());
                this.writer.flush();
                System.out.println("sending message '" + this.jTextField.getText() + "' to the server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.jTextField.setText("");
        this.jTextField.grabFocus();
    }

    private void run() {
        while (true) {
            String message = null;
            try {
                message = this.reader.readUTF();
            } catch (IOException e) {
                System.out.println("Error: " + e);
                try {
                    if (this.socket != null) {
                        this.socket.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            System.out.println("reading message '" + message + "' from the server");
            if (Objects.requireNonNull(message).contains(STOP_MESSAGE)) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            this.jTextArea.append(message + '\n');
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ClientGUI();
    }
}

/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework08.draft;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable {
    private Socket socket = null;
    private Server server = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream = null;
    private String name = null;

    public Client(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        System.out.println(socket + " " + server);
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.name = "";
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String string = this.dataInputStream.readUTF();
                if ("end".equalsIgnoreCase(string)) {
                    break;
                }
                this.server.broadcastMessage("[" + this.name + "] " + string);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (!this.name.isEmpty()) {
                this.server.remove(this);
                System.out.println("Клиент [" + this.name + "] отключен");
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                    System.out.println("Сокет закрыт");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessage(String message) {
        if (socket != null) {
            try {
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.assignments.week2.assignment08.draft;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Домашняя работа к теме №8 "Введение в сетевые протоколы".
 *
 * @author Александр Цупко
 */
public class Server {
    private List<Client> clients = new ArrayList<>();

    private Server() {
        try (ServerSocket serverSocket = new ServerSocket(3232)) {
            while (true) {
                System.out.println("Ожидание клиентов...");
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Сокет открыт");
                    Client client = new Client(socket, this);
                    this.add(client);
                    new Thread(client).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void add(Client client) {
        if (!this.clients.contains(client)) {
            this.clients.add(client);
        }
    }

    protected void broadcastMessage(String message) {
        LocalDateTime time = LocalDateTime.now();
        message = time + " " + message;
        for (Client client : this.clients) {
            client.sendMessage(message);
        }
    }

    public static void main(String[] args) {
        new Server();
    }

    public void remove(Client client) {
        if (this.clients.contains(client) && !client.getName().isEmpty()) {
            this.broadcastMessage("[" + client.getName() + "] вышел из комнаты чата");
            System.out.println("[" + client.getName() + "] вышел из комнаты чата");
        } else {
            System.out.println("Неавторизованный клиент вышел из команты чата");
        }
        this.clients.remove(client);
    }
}

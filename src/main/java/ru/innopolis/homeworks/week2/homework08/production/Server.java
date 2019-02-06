/*
 * Copyright (c) 2019. Alexander Tsupko (alexander.tsupko@outlook.com). All rights reserved.
 */

package ru.innopolis.homeworks.week2.homework08.production;

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
    private static final String STOP_MESSAGE = "quit";
    private static final int PORT = 3232;
    private static final int CLIENTS_MAX_NUMBER = 2;

    private List<Client> clients = new ArrayList<>();

    public Server() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Starting to wait on the port " + PORT);
            while (clients.size() <= CLIENTS_MAX_NUMBER) {
                System.out.println("Waiting for clients...");
                Socket socket = server.accept();
                System.out.println("Socket for the new client has opened");
                Client client = new Client(socket, this);
                this.add(client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            System.out.println("Error while establishing the server socket");
        }
        System.out.println("Server has exhausted its limit to the number of clients");
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

    protected void remove(Client client) {
        if (this.clients.contains(client) && !client.getName().isEmpty()) {
            this.broadcastMessage("[" + client.getName() + "] вышел из комнаты чата");
            System.out.println("[" + client.getName() + "] вышел из комнаты чата");
        } else {
            System.out.println("Неавторизованный клиент вышел из команты чата");
        }
        this.clients.remove(client);
    }

    public static void main(String[] args) {
        new Server();
    }
}

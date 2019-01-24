package ru.inno.lec08.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class MyServer {
    private static final String STOP_COMMAND = "exit";

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(Server.PORT);
        System.out.println("start working on port " + Server.PORT);
        Thread listener = new ListenerThread(serverSocket);
        listener.start();

        Scanner scanner = new Scanner(System.in);
        while (!STOP_COMMAND.equals(scanner.nextLine())) {

        }
        System.out.println("stopping server...");
        serverSocket.close();
        listener.interrupt();
        listener.join();
        System.out.println("server done");
    }
}

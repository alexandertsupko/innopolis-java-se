package ru.inno.lec08.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ListenerThread extends Thread {
    private final ServerSocket serverSocket;
    private final Map<Socket, String> socketUserMap;

    public ListenerThread(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        socketUserMap = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                socketUserMap.put(socket, "");
                // todo запустить поток-читатель
                System.out.println("Подключился " + socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

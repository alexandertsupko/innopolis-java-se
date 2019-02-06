package ru.innopolis.lectures.week2.lecture08.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final int PORT = 3232;
    private static List<Client> clients = new ArrayList<>();
    private static final String STOP_COMMAND = "quit";

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("start waiting on port = " + PORT);
        try (Socket socket = serverSocket.accept();
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if ("quit".equals(line)) {
                    break;
                }
                os.write(line.getBytes());
            }
        }
        System.out.println("server done");
    }
}

package ru.inno.lec08.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", Server.PORT);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream()) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Scanner scanner = new Scanner(System.in);
            String line;
            while ((line = scanner.nextLine()) != null) {
                os.write((line + "\n").getBytes());
                System.out.println(reader.readLine());
            }
        }
        System.out.println("client done");
    }
}

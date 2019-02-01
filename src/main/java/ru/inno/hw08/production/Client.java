package ru.inno.hw08.production;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

/**
 * Класс для обработки общения сервера с каждым клиентом в отдельном потоке.
 *
 * @author Александр Цупко
 */
public class Client implements Runnable {
    private static final String STOP_MESSAGE = "quit";

    private static int clientCounter = 0;

    private Socket socket;
    private Server server;
    private String name;

    private DataInputStream reader = null;
    private DataOutputStream writer = null;

    public Client(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.name = "Client #" + clientCounter++;
        try {
            this.reader = new DataInputStream(socket.getInputStream());
            this.writer = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error while obtaining data streams in the client constructor");
        }
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        if (!Objects.requireNonNull(this.socket).isClosed()) {
            try {
                this.writer.writeUTF(message);
                this.writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!Objects.requireNonNull(this.socket).isClosed()) { // while the socket for the current client is not closed
                String message = this.reader.readUTF(); // read a message from the client
                System.out.println(this.name + ": " + message); // log to the console
                if (STOP_MESSAGE.equalsIgnoreCase(message)) {
                    break; // quit if such a command has been input
                }
                this.server.broadcastMessage("[" + this.name + "] " + message);
                Thread.sleep(100); // add some delay before querying for the input to avoid hanging
            }
        } catch(IOException | InterruptedException e){
            System.out.println("Error: " + e);
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
}

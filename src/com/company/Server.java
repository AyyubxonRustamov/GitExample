package com.company;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;

    public Server(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for client...");

            socket = server.accept();
            System.out.println("Client accepted!");

            input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
            );

            String line = "";

            while (!line.equals("Over")) {

                try {
                    line = input.readUTF();
                    System.out.println("Message:" + line);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            System.out.println("Closing connection");


            input.close();
            socket.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}

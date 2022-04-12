package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public static void main(String[] args) {
        new Client("127.0.0.1", 5000);
    }

    public Client(String address, int port) {

        try {
            socket = new Socket(address, port);

            input = new DataInputStream(System.in);

            output = new DataOutputStream(socket.getOutputStream());

        } catch (IOException i) {
            System.out.println(i);
        }

        String line = "";

        while (!line.equals("Over")) {

            try {
                line = input.readLine();
                output.writeUTF(line);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

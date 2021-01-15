package com.company;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please supply a port number only to run the server.");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        System.out.println("Running server");

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            //Accept new client connections
            Socket newClient;
            while (true) {
                newClient = serverSocket.accept(); // Waits until a client connects
                System.out.println("Client connected!");
                EchoThread echoThread = new EchoThread(newClient);
                new Thread(echoThread).start(); // Handles that new client
            }
        } catch (IOException e) {
            System.out.println("Error when trying to listen on port " + portNumber);
            System.out.println(e.getMessage());
        }


    }


}

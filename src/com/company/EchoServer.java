package com.company;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) {
        //Check if a port number is supplied
        if (args.length != 1) {
            System.err.println("Please supply a port number only to run the server.");
            System.exit(1);
        }
        //Init given portNumber
        int portNumber = Integer.parseInt(args[0]);
        System.out.println("Running server");

        //Try connection at given portNumber
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            //Accept new client connections
            //Init New connection
            Socket newClient;
            //While server running accept multiple threads
            while (true) {
                newClient = serverSocket.accept(); // Waits until a client connects
                System.out.println("Client connected!"); //Notifies user of successful connection
                EchoThread echoThread = new EchoThread(newClient); //Create new EchoThread
                new Thread(echoThread).start(); // Handles that new client
            }
        } catch (IOException e) {
            //If problem connecting, notify the user
            System.out.println("Error when trying to listen on port " + portNumber);
            System.out.println(e.getMessage());
        }

    }


}

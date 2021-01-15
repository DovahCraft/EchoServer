package com.company;
import java.util.regex.*;
import java.net.*;
import java.io.*;


public class EchoServer {
    public static void main(String[] args){
        if(args.length != 1){
            System.err.println("Please supply a port number only to run the server.");
            System.exit(1);
        }


        int portNumber = Integer.parseInt(args[0]);
        System.out.println("Running server");

        try(ServerSocket serverSocket = new ServerSocket(portNumber))
            {
                //Accept new client connections
                while(true){
                    Thread echoThread = new Thread(new EchoThread(serverSocket.accept()));
                    echoThread.start();

                }

        }
        catch (IOException e){
            System.out.println("Error when tyring to listen on port "+ portNumber);
            System.out.println(e.getMessage());
        }



    }


}

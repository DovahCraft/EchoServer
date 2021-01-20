package com.company;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoThread implements Runnable {

    final String terminationWord = "quit"; //Reference for termination
    Socket clientSocket; //Current socket connection

    //Current echoThread
    public EchoThread(Socket inputSocket) {
        //Init clientSocket
        clientSocket = inputSocket;
    }

    public void run() {
      //Init the current Socket
      Socket clientSocket = this.clientSocket;
      try (PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true); //Write data to the client
           InputStreamReader fromClient = new InputStreamReader(clientSocket.getInputStream()); //Read data from the Client stream
           clientSocket) {
          //Init Variables
          int inputByte;
          char charFromClient ;
          int letterIndex = 0;

          // While the connection is open and the entire termination sequence hasn't been received
          while (letterIndex < 4 && (inputByte = fromClient.read()) != -1) {
            charFromClient  = (char) inputByte;
            //Check char type, if it is uppercase/lowercase english letter,
            // Ignore if not English letter
            if (isEnglishLetter(charFromClient )) {
              toClient.println(charFromClient );
              // if the letter is in the sequence
              if (terminationWord.charAt(letterIndex) == charFromClient ) {
                letterIndex++; // look for the next letter in the sequence
              }

              // if we get 'q', it is the start of a new sequence.
              else if (terminationWord.charAt(0) == charFromClient ) {
                letterIndex = 1; // second letter, look for 'u'
              }

              // else reset
              else {
                letterIndex = 0; // look for 'q'
              }
            }
          }
      }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //Notify the User the conncetion has closed
        System.out.println("Connection Closed");
    }

    //Checks if charFromClient  is valid English char
    private Boolean isEnglishLetter(char charFromClient ) {
        //If a valid ASCII character in the English alphabet, return true
        return ((charFromClient  >= 'A') && (charFromClient  <= 'Z')) ||
                ((charFromClient  >= 'a') && (charFromClient  <= 'z'));
    }
}

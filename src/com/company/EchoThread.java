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
          char inputChar;
          int letterIndex = 0;

          // While the connection is open and the entire termination sequence hasn't been received
          while (letterIndex < 4 && (inputByte = fromClient.read()) != -1) {
            inputChar = (char) inputByte;
            //Check char type, if it is uppercase/lowercase english letter,
            // Ignore if not English letter
            if (isEnglishLetter(inputChar)) {
              toClient.println(inputChar);
              // if the letter is in the sequence
              if (terminationWord.charAt(letterIndex) == inputChar) {
                letterIndex++; // look for the next letter in the sequence
              }

              // if we get 'q', it is the start of a new sequence.
              else if (terminationWord.charAt(0) == inputChar) {
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

    //Checks if inputChar is valid English char
    private Boolean isEnglishLetter(char inputChar) {
        //If a valid ASCII character in the English alphabet, return true
        return ((inputChar >= 'A') && (inputChar <= 'Z')) ||
                ((inputChar >= 'a') && (inputChar <= 'z'));
    }
}

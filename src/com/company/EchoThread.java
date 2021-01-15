package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoThread implements Runnable{
    Socket clientSocket;
    public EchoThread(Socket inputSocket){
        clientSocket = inputSocket;
    }
    public void run() {
        //Testing function for the regex for quit.
        testRegex();
        try(
        PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
        InputStreamReader fromClient = new InputStreamReader(clientSocket.getInputStream());
        )
        {
            int inputByte;
            char inputChar;
            while ((inputByte = fromClient.read()) != -1) {
                inputChar = (char) inputByte;
                //Check char type, if it is uppercase/lowercase english letter,
                if(isEnglishLetter(inputChar))
                    toClient.println("Valid char inputted: " + inputChar);
                //Otherwise dont

            }

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //Regex Testing Methods
    public static void testRegex(){
        String[] testStrs = {"quit", "q232uit2", "qekit", "q u i t"};
        System.out.println("Hello world!");
       // for(String i : testStrs ){
        //    quitDetector(i);
       // }
        /*System.out.println("Is English letter? a " + isEnglishLetter('a')); //true
        System.out.println("Is English letter? 8 " + isEnglishLetter('8')); //false
        System.out.println("Is English letter? A " + isEnglishLetter('A'));//true
        System.out.println("Is English letter? ! " + isEnglishLetter('!')); //false
        System.out.println("Is English letter? C " + isEnglishLetter('C')); //true*/

    }

    private static Boolean isEnglishLetter(char inputChar){
        if (((inputChar >= 'A') && (inputChar <= 'Z')) ||
                ((inputChar >= 'a') && (inputChar <= 'z'))){
            return true;
        }
        return false;
    }

    public static Boolean quitDetector(String input){
        String pattern = "q[\\W]*u[\\W]*i[\\W]*t[\\W]*";

        Pattern patternObj = Pattern.compile(pattern);

        Matcher m = patternObj.matcher(input);
        if(m.find()){
            System.out.println("Found value: " + m.group(0));
            return true;
        }
        //Otherwise, we didnt match.
        System.out.println("No match found!");
        return false;
    }
}

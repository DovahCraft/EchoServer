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
        //testRegex();
        try(
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        )
        {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
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
        for(String i : testStrs ){
            quitDetector(i);
        }
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

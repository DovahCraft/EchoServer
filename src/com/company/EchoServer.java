package com.company;
import java.util.regex.*;
import java.net.*;
import java.io.*;


public class EchoServer {
    public static void main(String[] args){
        String[] testStrs = {"quit", "quuit2", "qekit", "q u i t"};
        System.out.println("Hello world!");
        for(String i : testStrs ){
            quitDetector(i);
        }



    }

    public static Boolean quitDetector(String input){
        String pattern = "q{1}u{1}i{1}t{1}";

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

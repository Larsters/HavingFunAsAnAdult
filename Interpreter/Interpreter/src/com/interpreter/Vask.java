package com.interpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Vask {
    public static void main(String[] args) throws IOException {
        if(args.length > 1){        //basic shape of interpreter jvask
            System.out.println("Usage: jvask [script");
            System.exit(64);
        }else if(args.length==1){
            runFile(args[0]);
        }else{
            runPrompt();
        }
    }  
    //since lang is scripting (we exe it from sourse)
    //Interp supports running code by start from the command line
    //and give it a path to a file, it reads file and exe

    private static void runFile(String path) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if(hadError) System.exit(65);       // not to exe code with error
    }

    //or interactively
    //after running it this way it drops you in a prompt where you 
    //enter and exe code 1 line at a time

    private static void runPrompt() throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        for(;;){
            System.out.print("> ");
            String line = reader.readLine(); //reads input and return res
            if(line == null) break;          //when "end-of-life" => ret null
            run(line);
            hadError = false;               //not to kill whole session when have a mistake
        }                                   //also separating code that generates erroes 
}                                            //from the code that reports them :)  

    private static void run(String source){
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        //temporary
        for(Token token : tokens){
            System.out.println(token);
        }
    }

    //error handling 

    static void erroe(int line, String message){
        report(line, "", message);
    }
    private static void report(int line, String where, String message){
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
    static boolean hadError = false;


}

package com.interpreter;
import java.util.ArrayList;
import java.util.List;

//scanner
class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    Scanner(String source){
        this.source = source;
    }
    List<Token> scanTokens(){
        while(!isAtEnd()){
            //at the beginning of the next lexeme
            start = current;
            scanToken();
        }
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private void scanToken(){
        char c = advance();
        switch (c) {
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
    } 

    private final List<Token> tokens = new ArrayList<>();
    //i's that i into the string 
    private int start = 0;          //points to a first char in lexexme
    private int current = 0;        //points to currently considered
    private int line = 1;           //what source line currens is on
//for knowing the location
    Scanner(String source){
//f() to show if we consumed all the chars
private boolean isAtEnd(){
    return current >= source.length();
        }
    }
}

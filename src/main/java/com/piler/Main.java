package com.piler;

import java.util.List;

class Main {

    public static void main(String[] args) {
        String input = """
class Main {
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        boolean x = true && false; 
        int y = 10 + 12 * 3 - 1;
        int[] arr = new int[10];
        if (x || y > 1) {
            System.out.println("Hello, World!");
        } else {
            System.out.println("Goodbye, World!");
        }
    }
}""";
        List<Token> tokens = new Lexer().lex(input);
        
        // Exibir tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}

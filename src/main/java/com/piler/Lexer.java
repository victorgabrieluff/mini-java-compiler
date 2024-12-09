package com.piler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static final String EQUAL = "==";
    private static final String ASSIGN = "=";
    private static final String ID = "[a-zA-Z][a-zA-Z0-9]*";
    private static final String NUM = "[0-9]+";
    private static final String BOOLEAN = "boolean";
    private static final String CLASS = "class";
    private static final String EXTENDS = "extends";
    private static final String PUBLIC = "public";
    private static final String STATIC = "static";
    private static final String VOID = "void";
    private static final String MAIN = "main";
    private static final String STRING = "String";
    private static final String RETURN = "return";
    private static final String INT = "int";
    private static final String IF = "if";
    private static final String ELSE = "else";
    private static final String WHILE = "while";
    private static final String PRINT = "System.out.println";
    private static final String LENGTH = "length";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String THIS = "this";
    private static final String NEW = "new";
    private static final String NULL = "null";

    // private static final String WHITESPACE = "[ \n\t\r]+";
    private static final String COMMENT = "//.*";
    private static final String COMMENT_BLOCK = "/\\*(.|\\n)*?\\*/";
    private static final String GREATER_OR_EQUAL = ">=";
    private static final String LESS_OR_EQUAL = "<=";
    private static final String NOT_EQUAL = "!=";
    private static final String AND = "&&";
    private static final String OR = "\\|\\|";
    private static final String GREATER = ">";
    private static final String LESS = "<";
    private static final String LEFT_PARENTHESIS = "\\(";
    private static final String RIGHT_PARENTHESIS = "\\)";
    private static final String LEFT_BRACES = "\\{";
    private static final String RIGHT_BRACES = "\\}";
    private static final String LEFT_BRACKET = "\\[";
    private static final String RIGHT_BRACKET = "\\]";
    private static final String SEMICOLON = ";";
    private static final String COMMA = ",";
    private static final String DOT = "\\.";
    private static final String PLUS = "\\+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "\\*";
    private static final String NOT = "!";

    private static final Map<String, String> TOKEN_PATTERNS = new LinkedHashMap<>();

    static {
        TOKEN_PATTERNS.put(EQUAL, "<equal>");
        TOKEN_PATTERNS.put(ASSIGN, "<assign>");
        TOKEN_PATTERNS.put(BOOLEAN, "<boolean>");
        TOKEN_PATTERNS.put(CLASS, "<class>");
        TOKEN_PATTERNS.put(EXTENDS, "<extends>");
        TOKEN_PATTERNS.put(PUBLIC, "<public>");
        TOKEN_PATTERNS.put(VOID, "<void>");
        TOKEN_PATTERNS.put(MAIN, "<main>");
        TOKEN_PATTERNS.put(STRING, "<string>");
        TOKEN_PATTERNS.put(RETURN, "<return>");
        TOKEN_PATTERNS.put(INT, "<int>");
        TOKEN_PATTERNS.put(IF, "<if>");
        TOKEN_PATTERNS.put(ELSE, "<else>");
        TOKEN_PATTERNS.put(WHILE, "<while>");
        TOKEN_PATTERNS.put(PRINT, "<print>");
        TOKEN_PATTERNS.put(LENGTH, "<length>");
        TOKEN_PATTERNS.put(TRUE, "<true>");
        TOKEN_PATTERNS.put(FALSE, "<false>");
        TOKEN_PATTERNS.put(THIS, "<this>");
        TOKEN_PATTERNS.put(NEW, "<new>");
        TOKEN_PATTERNS.put(NULL, "<null>");
        TOKEN_PATTERNS.put(STATIC, "<static>");

        // TOKEN_PATTERNS.put(WHITESPACE, "<whitespace>");
        TOKEN_PATTERNS.put(COMMENT, "<comment>");
        TOKEN_PATTERNS.put(COMMENT_BLOCK, "<comment_block>");
        TOKEN_PATTERNS.put(GREATER_OR_EQUAL, "<greater_or_equal>");
        TOKEN_PATTERNS.put(LESS_OR_EQUAL, "<less_or_equal>");
        TOKEN_PATTERNS.put(NOT_EQUAL, "<not_equal>");
        TOKEN_PATTERNS.put(AND, "<and>");
        TOKEN_PATTERNS.put(OR, "<or>");
        TOKEN_PATTERNS.put(GREATER, "<greater>");
        TOKEN_PATTERNS.put(LESS, "<less>");
        TOKEN_PATTERNS.put(LEFT_PARENTHESIS, "<left_parenthesis>");
        TOKEN_PATTERNS.put(RIGHT_PARENTHESIS, "<right_parenthesis>");
        TOKEN_PATTERNS.put(LEFT_BRACES, "<left_braces>");
        TOKEN_PATTERNS.put(RIGHT_BRACES, "<right_braces>");
        TOKEN_PATTERNS.put(LEFT_BRACKET, "<left_bracket>");
        TOKEN_PATTERNS.put(RIGHT_BRACKET, "<right_bracket>");
        TOKEN_PATTERNS.put(SEMICOLON, "<semicolon>");
        TOKEN_PATTERNS.put(COMMA, "<comma>");
        TOKEN_PATTERNS.put(DOT, "<dot>");
        TOKEN_PATTERNS.put(PLUS, "<plus>");
        TOKEN_PATTERNS.put(MINUS, "<minus>");
        TOKEN_PATTERNS.put(MULTIPLY, "<multiply>");
        TOKEN_PATTERNS.put(NOT, "<not>");

        TOKEN_PATTERNS.put(NUM, "<num>");
        TOKEN_PATTERNS.put(ID, "<id>");
    }

    public List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        int position = 0;

        StringBuilder regexBuilder = new StringBuilder();
        for (String pattern : TOKEN_PATTERNS.keySet()) {
            regexBuilder.append("(").append(pattern).append(")|");
        }
        String regex = regexBuilder.substring(0, regexBuilder.length() - 1);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (position < input.length()) {
            matcher.region(position, input.length());

            if (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    if (matcher.group(i) != null) {
                        String tokenValue = matcher.group(i);
                        String tokenType = getTokenType(tokenValue);
                        tokens.add(new Token(tokenType, tokenValue));
                        position = matcher.end();
                        break;
                    }
                }
            } else {
                position++;
            }
        }
        return tokens;
    }

    private String getTokenType(String value) {
        for (Map.Entry<String, String> entry : TOKEN_PATTERNS.entrySet()) {
            if (value.matches(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "<unknown>";
    }
}
